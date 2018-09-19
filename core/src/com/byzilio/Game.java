package com.byzilio;

import com.badlogic.gdx.graphics.Texture;
import com.byzilio.engine.Engine;
import com.byzilio.engine.Entity;
import com.byzilio.engine.Scene;
import com.byzilio.game.components.сolliders.BoxCollider;
import com.byzilio.game.components.Position;
import com.byzilio.game.components.Rigidbody;
import com.byzilio.game.components.Sprite;
import com.byzilio.game.core.ArrayListEngine;
import com.byzilio.game.core.ArrayListEntity;
import com.byzilio.game.core.ArrayScene;
import com.byzilio.game.core.HashMapInput;
import com.byzilio.game.scripts.TestScript;
import com.byzilio.game.systems.InputSystem;
import com.byzilio.game.systems.LogTextSystem;
import com.byzilio.game.systems.MoveSystem;
import com.byzilio.game.systems.RenderSystem;
import com.byzilio.game.systems.ScriptSystem;

public class Game extends com.badlogic.gdx.Game {//Ну не умею я называть правильно классы

	/*
		Весь проект это написание движка и в последующем игры на шаблоне Entity-Component-System с помощью libGDX.
		Главная задача отказаться от приведений типов в игровом цикле,
		так как силшком долго в Java.

		Надо бы найди язык по лучше чем C++ и Java, а то слишком часто упираюсь в их "косяки"
		TODO: Переидти на Kotlin


		(не)Много глупых оправданий.
		Это все пробный вариант ECS.
		Я ECS пишу 3 раз в жизни.
		Так что код не был идеально продуман заранее.
		Поэтому тут очень много тупых мест появившихся из-за починки косяков.
		Например, функция create в Component, GameObject их не удобно использовать.
		Или что есть ArrayListContainer и ArrayListEntity c разницой в 2 строки.

		Я все больше склоняюсь к реализации через один абстракт класс GameObject(другой не текущий).
		Но это сильно увеличит и усложнит код.
	 */

	Engine engine;

	@Override
	public void create () {
		engine = new ArrayListEngine(new HashMapInput());
		engine.add(new InputSystem());
        engine.add(new MoveSystem());
        engine.add(new ScriptSystem());
		engine.add(new LogTextSystem());
		engine.add(new RenderSystem());

		Scene scene = new ArrayScene();

		Entity e = new ArrayListEntity();
		/*
		scene.add(e);
		e.add(new LogTextComponent("aAAAAAA"));
		e.add(new LogTextComponent("134134124"));
		e.add(new LogTextComponent());
		e.add(new LogTextComponent());
		e.add(new LogTextComponent("adfghfadshafs"));

		e = new ArrayListEntity();
		scene.add(e);
		e.add(new Position(10, 10));
		e.add(new Sprite(new Texture("badlogic.jpg"), 100, 120));

		e = new ArrayListEntity();
		scene.add(e);
		e.add(new Position(110, 10));
		e.add(new Sprite(new Texture("badlogic.jpg"), 50, 220));

		e = new ArrayListEntity();
		*/
		engine.changeScene(scene);


		e.add(new Position(0, 30));
		e.add(new Sprite(1, new Texture("badlogic.jpg"), 100, 150));
        //e.add(new Rigidbody(2,1,1.5f,1,2,2,0,0,0));
		e.add(new Rigidbody(0,0,0,0,10,10,0,0.2f,0));
		e.add(new TestScript());
		e.add(new BoxCollider(false,100,150));
		scene.add(e);


		setScreen(engine);

		e = new ArrayListEntity();
		e.add(new Position(100, 100));
		e.add(new Sprite(0, new Texture("test1.png"), 200, 200));
		e.add(new BoxCollider(true,200,200));

		engine.getScene().add(e);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		engine.dispose();
		//dispose в libGDX обязателен его же обьекты нужно таким образом удалять.
		//Иначе на андроиде будет мусор вместо текстур например.
	}
}
