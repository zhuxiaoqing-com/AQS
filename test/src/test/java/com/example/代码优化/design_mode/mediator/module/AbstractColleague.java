package com.example.代码优化.design_mode.mediator.module;

import com.example.代码优化.design_mode.mediator.AbstractMediator;

public abstract class AbstractColleague {
    protected AbstractMediator mediator;

    public AbstractColleague(AbstractMediator _mediator) {
        this.mediator = _mediator;
    }
}
