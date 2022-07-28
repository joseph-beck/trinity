package trin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import components.Sprite;
import components.SpriteRenderer;
import components.Spritesheet;
import imgui.ImGui;
import org.joml.Vector2f;
import org.joml.Vector4f;
import util.AssetPool;

public class LevelEditorScene extends Scene {
    private GameObject obj1;
    private Spritesheet sprites;
    private SpriteRenderer obj1Sprite;

    @Override
    public void init() {
        loadResources();

        this.camera = new Camera(new Vector2f(0, 0));

        if (levelLoaded) {
            return;
        }

        sprites = AssetPool.getSpriteSheet("assets/images/spritesheet.png");

        obj1 = new GameObject("Object 1", new Transform(new Vector2f(200, 100), new Vector2f(256, 256)), 3);
        obj1Sprite = new SpriteRenderer();
        obj1.addComponent(obj1Sprite);
        obj1Sprite.setColor(new Vector4f(1, 0, 0, 1));
        obj1.addComponent(obj1Sprite);
        this.addGameObjectToScene(obj1);
        this.activeGameObject = obj1;

        GameObject obj2 = new GameObject("Object 2", new Transform(new Vector2f(400, 100), new Vector2f(256, 256)), 2);
        SpriteRenderer obj2SpriteRender = new SpriteRenderer();
        Sprite obj2Sprite = new Sprite();
        obj2Sprite.setTexture(AssetPool.getTexture("assets/images/blendImage2.png"));
        obj2SpriteRender.setSprite(obj2Sprite);
        obj2.addComponent(obj2SpriteRender);
        this.addGameObjectToScene(obj2);


    }

    private void loadResources() {
        AssetPool.getShader("assets/shaders/default.glsl");

        AssetPool.addSpriteSheet(   "assets/images/spritesheet.png",new Spritesheet(AssetPool.getTexture("assets/images/spritesheet.png"),
                                    16, 16, 26, 0));
    }

    @Override
    public void update(float dt) {
        /*Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        System.out.print(gson.toJson(obj1Sprite));*/
        //System.out.println("" + (1.0f / dt) + "FPS");
        //obj1.transform.position.x += 10 * dt;

        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }

        this.renderer.render();
    }

    @Override
    public void imGui() {
        ImGui.begin("Test window");
        ImGui.text("Stuff");
        ImGui.end();
    }
}
