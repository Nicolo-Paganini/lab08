package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

public final class DrawNumberStandardOutputView implements DrawNumberView{

    public DrawNumberStandardOutputView(){
        /**
         * Only for avoiding warning
         */
    }

    @Override
    public void setController(final DrawNumberController observer){
        /**
         * Only output this UI, so nothing here
         */
    }

    @Override
    public void start(){
        /**
         * Only output this UI, so nothing here
         */
    }
    
    @Override
    public void result(final DrawResult res){
        System.out.println(res.getDescription());
    }
}