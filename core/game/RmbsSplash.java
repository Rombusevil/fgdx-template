package com.rombus.evilbones.template.game;

import org.flixel.FlxG;
import org.flixel.FlxSound;
import org.flixel.FlxSprite;
import org.flixel.FlxState;
import org.flixel.FlxTimer;
import org.flixel.event.IFlxTimer;
import org.flixel.plugin.tweens.TweenPlugin;
import org.flixel.plugin.tweens.TweenSprite;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;

import com.rombus.evilbones.template.utils.AssetsLocator;
import com.rombus.evilbones.template.utils.Constants;

/**
 * For creating splash screens.
 * It fades in an image and plays an audio file.
 * When done it switches to the next FlxState (another splash/a main menu/etc).
 *
 * @author Rombus
 *
 * 07/05/2016 20:03:06
 */
 public class RmbsSplash extends FlxState {
 	private static final float TIME_FI = 2f, TIME = 4.5f;
 	private float timeFadeIn, time;
 	private FlxSprite image;
 	private FlxSound sound;
 	private FlxTimer timer;
 	private IFlxTimer switchStateCallback;

     private FlxState nextState = null;
     private String simage, ssound;

     // Override this method to configure the RmbsSplash
 	public void setup(){
 		this.nextState = new MenuState();
 		this.simage = AssetsLocator.SPLASH;
 		this.ssound = AssetsLocator.SPLASH_SFX;
 		this.timeFadeIn = TIME_FI;
 		this.time = TIME;
 	}


 	@Override
 	public void create() {
 		setup();

         this.image = new FlxSprite(0, 0, this.simage);
         this.sound = new FlxSound();
         this.sound.loadEmbedded(this.ssound, false, true);
         this.timer = new FlxTimer();
         this.switchStateCallback = new IFlxTimer() {
             @Override
             public void callback(FlxTimer Timer) {
                 FlxG.switchState(nextState);
             }
         };

 		sound.play(true);

 		int scale = getImageScale(image);
 		image.scale.x = image.scale.y = scale;

 		// Center image
 		image.origin.x = image.width / 2;
 		image.origin.y = image.height/ 2 ;
 		image.x = FlxG.width / 2 - image.width / 2;
 		image.y = FlxG.height/ 2 - image.height/ 2;

 		image.setAlpha(0);
 		add(image);

 		Tween.to(image, TweenSprite.ALPHA, timeFadeIn)
 			.target(1)
 			.setCallback(new TweenCallback() {
 				@Override
 				public void onEvent(int type, BaseTween<?> source) {
 					timer.start(time, 1, switchStateCallback);
 				}
 			})
 			.start(TweenPlugin.manager);
 	}

 	// Returns the scale neede for the image to take as much screen space as possible.
 	private int getImageScale(FlxSprite image){
 		float sw = image.width;
 		float sh = image.height;
 		int scale = 1;

 		if(sw >= sh){
 			while(sw < Constants.WIDTH_RESOLUTION){
 				sw*=2;
 				scale++;
 			}
 		} else {
 			while(sh < Constants.HEIGHT_RESOLUTION){
 				sh*=2;
 				scale++;
 			}
 		}

 		return scale;
 	}

 	@Override
 	public void destroy() {
 		image.destroy();
 		sound.destroy();
 		timer.destroy();

 		super.destroy();
 	}
 }
