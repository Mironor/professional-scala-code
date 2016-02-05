package com.professionalscala.ch3.java;

import com.professionalscala.ch3.ScalaTrait;
import com.professionalscala.ch3.ScalaTrait$class;

public class JavaUseOfTrait implements ScalaTrait {
    @Override
    public void print(){
        ScalaTrait$class.print(this);
    }
}
