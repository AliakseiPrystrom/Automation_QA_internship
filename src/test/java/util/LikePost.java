package util;

import aquality.selenium.elements.interfaces.IButton;
import org.openqa.selenium.By;

import static aquality.selenium.browser.AqualityServices.getElementFactory;

public class LikePost {
    public static void likePost(String idUser,String idPost){
        IButton likeButton = getElementFactory().getButton
                (By.xpath(String.format("//div[@class='like_wrap _like_wall%s_%s ']/descendant::div[@onclick='Reactions.onReactionClick(this, event)']",idUser,idPost)),"like post");
        likeButton.click();
    }
}
