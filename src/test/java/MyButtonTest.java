import org.junit.Assert;
import org.junit.Test;
import org.pio.ui.buttons.AbstractMyButton;
import org.pio.ui.buttons.MyButton;

import java.awt.image.BufferedImage;

public class MyButtonTest {

    @Test
    public void testCreateButtonWithoutTextAndImage() {
        String buttonName = "ButtonWithoutTextAndImage";
        int posX = 0;
        int posY = 0;
        int width = 10;
        int height = 10;
        int id = 0;

        AbstractMyButton button = MyButton.createButtonWithoutTextAndImage(buttonName, posX, posY, width, height, id);

        Assert.assertNotNull(button);

        Assert.assertNull(button.getText());
        Assert.assertNull(button.getImage());

        Assert.assertTrue(button.getName().equals(buttonName));
        Assert.assertTrue(button.getPosX()==posX);
        Assert.assertTrue(button.getPosY()==posY);
        Assert.assertTrue(button.getWidth()==width);
        Assert.assertTrue(button.getHeight()==height);
        Assert.assertTrue(button.getId()==id);
    }

    @Test
    public void testCreateButtonWithText() {
        String buttonName = "ButtonWithText";
        int posX = 0;
        int posY = 0;
        int width = 10;
        int height = 10;
        int id = 0;
        String text = "Text";

        AbstractMyButton button = MyButton.createButtonWithText(buttonName, posX, posY, width, height, id, text);
        Assert.assertNotNull(button);

        Assert.assertTrue(button.getText().equals(text));
        Assert.assertNull(button.getImage());

        Assert.assertTrue(button.getName().equals(buttonName));
        Assert.assertTrue(button.getPosX()==posX);
        Assert.assertTrue(button.getPosY()==posY);
        Assert.assertTrue(button.getWidth()==width);
        Assert.assertTrue(button.getHeight()==height);
        Assert.assertTrue(button.getId()==id);

    }

    @Test
    public void testCreateButtonWithImage() {
        String buttonName = "ButtonWithImage";
        int posX = 0;
        int posY = 0;
        int width = 10;
        int height = 10;
        int id = 0;
        BufferedImage image = new BufferedImage(1,1,1);

        AbstractMyButton button = MyButton.createButtonWithImage(buttonName, posX, posY, width, height, id, image);
        Assert.assertNotNull(button);

        Assert.assertNull(button.getText());
        Assert.assertNotNull(button.getImage());

        Assert.assertTrue(button.getName().equals(buttonName));
        Assert.assertTrue(button.getPosX()==posX);
        Assert.assertTrue(button.getPosY()==posY);
        Assert.assertTrue(button.getWidth()==width);
        Assert.assertTrue(button.getHeight()==height);
        Assert.assertTrue(button.getId()==id);
    }

    @Test
    public void testCreateButtonWithTextAndImage() {
        String buttonName = "ButtonWithTextAndImage";
        int posX = 0;
        int posY = 0;
        int width = 10;
        int height = 10;
        int id = 0;
        String text = "Text";
        BufferedImage image = new BufferedImage(1,1,1);

        AbstractMyButton button = MyButton.createButtonWithTextAndImage(buttonName, posX, posY, width, height, id, text, image);
        Assert.assertNotNull(button);

        Assert.assertTrue(button.getText().equals(text));
        Assert.assertNotNull(button.getImage());

        Assert.assertTrue(button.getName().equals(buttonName));
        Assert.assertTrue(button.getPosX()==posX);
        Assert.assertTrue(button.getPosY()==posY);
        Assert.assertTrue(button.getWidth()==width);
        Assert.assertTrue(button.getHeight()==height);
        Assert.assertTrue(button.getId()==id);
    }

}
