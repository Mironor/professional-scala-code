package com.wrox.professionalscala.ch03.java;

import com.wrox.professionalscala.ch03.ScalaTrait;
import com.wrox.professionalscala.ch03.ScalaTrait$class;

public class JavaUseOfTrait implements ScalaTrait {
    @Override
    public void print(){
        ScalaTrait$class.print(this);
    }
}
