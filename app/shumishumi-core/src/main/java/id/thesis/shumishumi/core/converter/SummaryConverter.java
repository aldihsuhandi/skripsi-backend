package id.thesis.shumishumi.core.converter;

import id.thesis.shumishumi.facade.model.summary.CartSummary;
import id.thesis.shumishumi.facade.model.summary.CommentSummary;
import id.thesis.shumishumi.facade.model.summary.ItemSummary;
import id.thesis.shumishumi.facade.model.summary.PostSummary;
import id.thesis.shumishumi.facade.model.summary.UserSummary;
import id.thesis.shumishumi.facade.model.viewobject.CartVO;
import id.thesis.shumishumi.facade.model.viewobject.CommentVO;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.facade.model.viewobject.PostVO;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;

public class SummaryConverter {
    public static ItemSummary toSummary(ItemVO vo, int totalWishlist) {
        if (vo == null) {
            return null;
        }
        ItemSummary summary = toSummary(vo);
        summary.setTotalWishlist(totalWishlist);

        return summary;
    }

    private static ItemSummary toSummary(ItemVO vo) {
        if (vo == null) {
            return null;
        }

        ItemSummary summary = new ItemSummary();
        summary.setItemId(vo.getItemId());
        summary.setItemName(vo.getItemName());
        summary.setItemPrice(vo.getItemPrice());
        summary.setItemImages(vo.getItemImages());
        summary.setItemDescription(vo.getItemDescription());
        summary.setItemQuantity(vo.getItemQuantity());
        summary.setMerchantInfo(toSummary(vo.getMerchantInfo()));
        summary.setMerchantLevel(vo.getMerchantLevel().getInterestLevelName());
        summary.setHobby(vo.getHobby().getHobbyName());
        summary.setItemCategory(vo.getItemCategory().getCategoryName());
        summary.setGmtCreate(vo.getGmtCreate());
        summary.setGmtModified(vo.getGmtModified());
        return summary;
    }

    public static UserSummary toSummary(UserVO vo) {
        if (vo == null) {
            return null;
        }
        UserSummary userSummary = new UserSummary();
        userSummary.setEmail(vo.getEmail());
        userSummary.setUsername(vo.getUsername());
        userSummary.setProfilePicture(vo.getProfilePicture());
        userSummary.setPhoneNumber(vo.getPhoneNumber());
        userSummary.setRole(vo.getRoleVO().getRoleName());
        userSummary.setGender(vo.getGender());
        userSummary.setLocation(vo.getLocation());
        userSummary.setDateOfBirth(vo.getDateOfBirth());
        userSummary.setGmtCreate(vo.getGmtCreate());
        userSummary.setGmtModified(vo.getGmtModified());

        return userSummary;
    }

    public static PostSummary toSummary(PostVO vo, int currentUserVote) {
        if (vo == null) {
            return null;
        }

        PostSummary summary = new PostSummary();
        summary.setPostId(vo.getPostId());
        summary.setTitle(vo.getTitle());
        summary.setContent(vo.getContent());
        summary.setTags(vo.getTags());
        summary.setImages(vo.getImages());
        summary.setUpvote(vo.getUpvote());
        summary.setDownvote(vo.getDownvote());
        summary.setCurrentUserVote(vo.getCurrentUserVote());
        summary.setGmtCreate(vo.getGmtCreate());
        summary.setGmtModified(vo.getGmtModified());
        summary.setCurrentUserVote(currentUserVote);

        return summary;
    }

    public static CommentSummary toSummary(CommentVO vo) {
        if (vo == null) {
            return null;
        }

        CommentSummary summary = new CommentSummary();
        summary.setCommentId(vo.getCommentId());
        summary.setContent(vo.getContent());
        summary.setCommenter(toSummary(vo.getCommenter()));
        summary.setUpvote(vo.getUpvote());
        summary.setDownvote(vo.getDownvote());
        summary.setCurrentUserVote(vo.getCurrentUserVote());

        return summary;
    }

    public static CartSummary toSummary(CartVO vo) {
        if (vo == null) {
            return null;
        }

        CartSummary summary = new CartSummary();
        summary.setItemSummary(toSummary(vo.getItem()));
        summary.setQuantity(vo.getQuantity());
        summary.setGmtCreate(vo.getGmtCreate());
        summary.setGmtModified(vo.getGmtModified());

        return summary;
    }
}
