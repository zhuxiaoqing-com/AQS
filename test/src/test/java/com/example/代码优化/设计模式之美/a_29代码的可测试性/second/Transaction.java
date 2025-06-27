package com.example.代码优化.设计模式之美.a_29代码的可测试性.second;

import com.example.代码优化.设计模式之美.a_29代码的可测试性.IdGenerator;
import com.example.代码优化.设计模式之美.a_29代码的可测试性.RedisDistributedLock;
import com.example.代码优化.设计模式之美.a_29代码的可测试性.WalletRpcService;

import javax.transaction.InvalidTransactionException;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2020/6/11 15:10
 * @Description:
 */
public class Transaction {
	private final static int TO_BE_EXECUTE = 1;
	private final static int EXECUTED = 2;
	private final static int EXPIRED = 3;
	private final static int FAILED = 4;


	private String id;
	private Long buyerId;
	private Long sellerId;
	private Long productId;
	private String orderId;
	private Long createTimestamp;
	private Double amount;
	private int status;
	private String walletTransactionId;

	private WalletRpcService walletRpcService;
	private RedisDistributedLock lock;

	public Transaction(String preAssignedId, Long buyerId, Long sellerId, Long productId, String orderId) {
		fillTransactionId(preAssignedId);

		if (!this.id.startsWith("t_")) {
			this.id = "t_" + preAssignedId;
		}

		this.buyerId = buyerId;
		this.sellerId = sellerId;
		this.productId = productId;
		this.orderId = orderId;
		this.status = Transaction.TO_BE_EXECUTE;
		this.createTimestamp = System.currentTimeMillis();
	}

	private void fillTransactionId(String preAssignedId) {
		if (preAssignedId != null && !preAssignedId.isEmpty()) {
			this.id = preAssignedId;
		} else {
			this.id = IdGenerator.generateTransactionId();
		}
	}

	public boolean execute() throws InvalidTransactionException {
		if (buyerId == null || (sellerId == null || amount < 0.0)) {
			throw new InvalidTransactionException("");
		}
		if (status == Transaction.EXECUTED) {
			return true;
		}

		boolean isLocked = false;
		try {
			isLocked = lock.lockTransction(id);
			if (!isLocked) {
				return false; // 锁定未成功，返回 false， job 兜底执行
			}

			if (status == Transaction.EXECUTED) { // double check 加锁后重新检测一次
				return true;
			}

			if (isExpired()) {
				return false;
			}

			String walletTransactionId = walletRpcService.moveMoney(id, buyerId, sellerId);
			if (walletTransactionId != null) {
				this.walletTransactionId = walletTransactionId;
				this.status = Transaction.EXECUTED;
				return true;
			} else {
				this.status = Transaction.FAILED;
				return false;
			}
		} finally {
			if (isLocked) {
				lock.unlockTransction(id);
			}
		}
	}

	private boolean isExpired() {
		long executionInvokedTimestamp = System.currentTimeMillis();
		if (executionInvokedTimestamp - createTimestamp > 14/*14天*/) {
			this.status = Transaction.EXPIRED;
			return true;
		}
		return false;
	}
}
