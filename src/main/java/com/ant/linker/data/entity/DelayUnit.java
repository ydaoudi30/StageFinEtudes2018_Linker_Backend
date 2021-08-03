package com.ant.linker.data.entity;

import java.io.Serializable;

public enum DelayUnit implements Serializable {
  Day ("Day"),
  Week ("Week"),
  Month ("Month");
  
  private String delay = "";
  
  DelayUnit(String delay){
    this.delay = delay;
  }
  
  public String getDelay(){
	  return this.delay;
  }
   
  public String toString(){
    return delay;
  }

  
}