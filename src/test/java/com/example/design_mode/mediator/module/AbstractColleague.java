package com.example.design_mode.mediator.module;

import com.example.design_mode.mediator.AbstractMediator;

public abstract class AbstractColleague {
    protected AbstractMediator mediator;

    public AbstractColleague(AbstractMediator _mediator) {
        this.mediator = _mediator;
    }
}
