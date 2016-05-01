package com.wrox.professionalscala.ch3.java;

import com.wrox.professionalscala.ch3.ScalaTrait;
import com.wrox.professionalscala.ch3.ScalaTrait$class;

public class JavaUseOfTrait implements ScalaTrait {
    @Override
    public void print(){
        ScalaTrait$class.print(this);
    }
}
