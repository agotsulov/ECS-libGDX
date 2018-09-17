package com.byzilio.engine;

import com.badlogic.gdx.Screen;
import com.byzilio.engine.Scene;
import com.byzilio.engine.System;
import com.byzilio.engine.core.Container;
import com.byzilio.engine.core.Input;

public abstract class Engine implements Screen,Container<System> {

    public Input input;

    public Engine(Input input) {
        this.input = input;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public abstract void changeScene(Scene scene);

}