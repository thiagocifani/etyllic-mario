package mario.stages;

import sound.model.Music;
import mario.character.Character;
import mario.character.CharacterControl;

import mario.item.fruit.RedFruit;
import br.com.etyllica.animation.scripts.FrameAnimation;
import br.com.etyllica.core.application.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.video.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class YoshiHouse extends Application{

	public YoshiHouse(int w, int h) {
		super(w, h);
	}

	private Music music;
	private Character mario;
    private Character luigi;
	private ImageLayer background;

	private int groundPosition = 163;
	
	private RedFruit[] fruits;

	@Override
	public void load() {

		loadingPhrase = "Loading Resources...";

		//By default, engine looks for image at /bin/res/images folder
		background = new ImageLayer("yoshihouse.png");
		
		fruits = new RedFruit[7];
		fruits[0] = new RedFruit(32, 60);
		fruits[1] = new RedFruit(48, 76);
		fruits[2] = new RedFruit(96, 60);
		fruits[3] = new RedFruit(78, 92);
		fruits[4] = new RedFruit(114, 76);
		fruits[5] = new RedFruit(176, 60);
		fruits[6] = new RedFruit(208, 76);
		
		for(RedFruit fruit: fruits){
			animation.add(new FrameAnimation(fruit));
		}
		
		loading = 20;

		mario = new Character(30, groundPosition, 32,32,"mario");
		luigi = new Character(120, groundPosition, 32, 32, "luigi" );
		
		mario.load();
		luigi.load();
		
		loading = 60;
		loading = 80;

		music = new Music("Yoster Island.mp3");
		//music.play();
		updateAtFixedRate(40);
		
		loading = 100;

	}
	
	@Override
	public void timeUpdate(){
		
		mario.preAnima();
		luigi.preAnima();

	}

	@Override
	public void draw(Graphic g) {

		background.draw(g);
		
		for(RedFruit fruit: fruits){
			fruit.draw(g);	
		}
		
		mario.draw(g);
		luigi.draw(g);

	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {

		new CharacterControl(mario).keyboardEvents(event);
		new CharacterControl(luigi).keyboardEvents(event);

		return GUIEvent.NONE;
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {

		return GUIEvent.NONE;
	}
}
