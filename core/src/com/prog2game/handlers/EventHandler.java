package com.prog2game.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.Random;

public class EventHandler {

    Skin skin = new Skin(Gdx.files.internal("skin/Holo-dark-hdpi.json"));
    public static class Knight extends Dialog {

        public Knight(String title, Skin skin) {
            super(title, skin);
        }

        public Knight(String title, Skin skin, String windowStyleName) {
            super(title, skin, windowStyleName);
        }

        public Knight(String title, WindowStyle windowStyle) {
            super(title, windowStyle);
        }



        /// Code under here will executed no matter which method used from above
        {


            Random rand = new Random();
            int n = rand.nextInt(2);

            String event_text = "yes";
            String button1_text = "Yes";
            String button2_text = "No";

            switch (n) {
                case 0:
                    text("You found a mystery potion");
                    button("Drink it","0");
                    button("Throw it away","100");
                     break;
                case 1:
                    text("You can across a suspicious looking chest");
                    button("Open it","1");
                    button("Ignore it","100");
                    break;
//                case 2:
//                    text("ssdda");
//                    button("sd","2");
//                    button("sd","100");
//                    break;
//                case 3:
//                    text("ssddd");
//                    button("sd","3");
//                    button("sd","100");
//                    break;
//                case 4:
//                    text("ssssdd");
//                    button("ssdd","4");
//                    button("sd","100");
//                    break;

            }

        }
    }





}
