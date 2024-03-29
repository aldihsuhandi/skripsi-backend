package id.thesis.shumishumi.core.converter;

import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.summary.CartSummary;
import id.thesis.shumishumi.facade.model.summary.CommentSummary;
import id.thesis.shumishumi.facade.model.summary.HistoryItemSummary;
import id.thesis.shumishumi.facade.model.summary.ItemSummary;
import id.thesis.shumishumi.facade.model.summary.PostSummary;
import id.thesis.shumishumi.facade.model.summary.ReviewSummary;
import id.thesis.shumishumi.facade.model.summary.TransactionDetailSummary;
import id.thesis.shumishumi.facade.model.summary.TransactionSummary;
import id.thesis.shumishumi.facade.model.summary.UserSummary;
import id.thesis.shumishumi.facade.model.viewobject.CartVO;
import id.thesis.shumishumi.facade.model.viewobject.CommentVO;
import id.thesis.shumishumi.facade.model.viewobject.HistoryItemVO;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.facade.model.viewobject.PostVO;
import id.thesis.shumishumi.facade.model.viewobject.ReviewVO;
import id.thesis.shumishumi.facade.model.viewobject.TransactionVO;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SummaryConverter {
    public static ItemSummary toSummary(ItemVO vo, int totalWishlist) {
        if (vo == null) {
            return null;
        }
        ItemSummary summary = toSummary(vo);
        summary.setTotalWishlist(totalWishlist);

        return summary;
    }

    public static ItemSummary toSummary(ItemVO vo) {
        if (vo == null) {
            return null;
        }

        ItemSummary summary = new ItemSummary();
        summary.setItemId(vo.getItemId());
        summary.setPostId(vo.getPostId());
        summary.setItemName(vo.getItemName());
        summary.setItemPrice(vo.getItemPrice());
        summary.setItemImages(vo.getItemImages());
        summary.setItemDescription(vo.getItemDescription());
        summary.setItemQuantity(vo.getItemQuantity());
        summary.setMerchantInfo(toSummary(vo.getMerchantInfo()));
        summary.setMerchantLevel(vo.getMerchantLevel().getInterestLevelName());
        summary.setHobby(vo.getHobby().getHobbyName());
        summary.setReview(FunctionUtil.roundDouble(vo.getReview()));
        summary.setItemCategory(vo.getItemCategory().getCategoryName());
        summary.setGmtCreate(vo.getGmtCreate());
        summary.setGmtModified(vo.getGmtModified());
        summary.setUserLevel(vo.getUserLevel() == null ?
                "" : vo.getUserLevel().getInterestLevelName());

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
        userSummary.setReview(FunctionUtil.roundDouble(vo.getReview()));
        userSummary.setCanTelegram(Boolean.parseBoolean(vo.getExtendInfo().
                getOrDefault(CommonConst.EXTEND_INFO_TELEGRAM, "false")));
        userSummary.setCanWhatsapp(Boolean.parseBoolean(vo.getExtendInfo().
                getOrDefault(CommonConst.EXTEND_INFO_WHATSAPP, "false")));

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
        summary.setGmtCreate(vo.getGmtCreate());
        summary.setGmtModified(vo.getGmtModified());

        return summary;
    }

    public static CartSummary toSummary(CartVO vo) {
        if (vo == null) {
            return null;
        }

        CartSummary summary = new CartSummary();
        summary.setItemSummary(toSummary(vo.getItem()));
        summary.setQuantity(vo.getQuantity());
        summary.setSelected(vo.isSelected());
        summary.setGmtCreate(vo.getGmtCreate());
        summary.setGmtModified(vo.getGmtModified());

        return summary;
    }

    public static TransactionSummary toSummary(TransactionVO vo) {
        if (vo == null) {
            return null;
        }

        Map<String, List<TransactionDetailSummary>> details = new HashMap<>();
        if (!CollectionUtils.isEmpty(vo.getDetails())) {
            vo.getDetails().stream().map(detail -> {
                TransactionDetailSummary detailSummary = new TransactionDetailSummary();
                detailSummary.setItem(toSummary(detail.getHistoryItemVO()));
                detailSummary.setQuantity(detail.getQuantity());

                return detailSummary;
            }).forEach(detail -> {
                String merchant = detail.getItem().getMerchantInfo().getUsername();
                List<TransactionDetailSummary> detailList = new ArrayList<>();
                if (details.containsKey(merchant)) {
                    detailList = details.get(merchant);
                }

                detailList.add(detail);
                details.put(merchant, detailList);
            });
        }

        TransactionSummary summary = new TransactionSummary();
        summary.setTransactionId(vo.getTransactionId());
        summary.setPrice(vo.getPrice());
        summary.setStatus(vo.getStatus());
        summary.setPaymentType(vo.getPaymentType());
        summary.setPaymentCode(vo.getMidtransLink());
        summary.setDetails(details);
        summary.setGmtCreate(vo.getGmtCreate());
        summary.setGmtModified(vo.getGmtModified());

        return summary;
    }

    public static HistoryItemSummary toSummary(HistoryItemVO vo) {
        if (vo == null) {
            return null;
        }

        HistoryItemSummary summary = new HistoryItemSummary();
        summary.setItemId(vo.getItem().getItemId());
        summary.setItemName(vo.getItem().getItemName());
        summary.setItemPrice(vo.getItem().getItemPrice());
        summary.setItemImages(vo.getItem().getItemImages());
        summary.setItemDescription(vo.getItem().getItemDescription());
        summary.setMerchantInfo(toSummary(vo.getItem().getMerchantInfo()));
        summary.setMerchantLevel(vo.getItem().getMerchantLevel().getInterestLevelName());
        if (vo.getItem().getUserLevel() != null) {
            summary.setUserLevel(vo.getItem().getUserLevel().getInterestLevelName());
        }
        summary.setHobby(vo.getItem().getHobby().getHobbyName());
        summary.setItemCategory(vo.getItem().getItemCategory().getCategoryName());
        summary.setGmtCreate(vo.getGmtCreate());
        summary.setGmtModified(vo.getGmtModified());

        return summary;
    }

    public static ReviewSummary toSummary(ReviewVO vo) {
        if (vo == null) {
            return null;
        }
        ReviewSummary summary = new ReviewSummary();
        summary.setReviewId(vo.getReviewId());
        summary.setItemId(vo.getItemId());
        summary.setReview((int) FunctionUtil.roundDouble(vo.getStar()));
        summary.setImages(vo.getReviewImages());
        summary.setInterestLevel(vo.getInterestLevel().getInterestLevelName());
        summary.setDescription(vo.getDescription());
        summary.setNeedReview(vo.isNeedReview());

        return summary;
    }
}
