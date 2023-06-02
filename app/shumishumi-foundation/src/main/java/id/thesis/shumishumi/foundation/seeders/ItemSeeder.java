package id.thesis.shumishumi.foundation.seeders;

import id.thesis.shumishumi.common.service.PostService;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.enumeration.InterestLevelEnum;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.viewobject.PostVO;
import id.thesis.shumishumi.foundation.model.result.ItemDO;
import id.thesis.shumishumi.foundation.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ItemSeeder extends BaseSeeder {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PostService postService;

    @Override
    void setOrder() {
        this.order = 10;
    }

    @Override
    void setSeederName() {
        this.seederName = "item seeder";
    }

    @Override
    void deleteRecords() {
        itemRepository.deleteAll();
    }

    @Override
    void seed() {
        List<ItemRequest> r = new ArrayList<>();

        // KeyboardShop
        // KEYBOARD
        // KEYBOARD_SWITCH
        r.add(new ItemRequest("Gateron Yellow", 3500L, "Gateron yellow", 999,
                Collections.singletonList("https://alexnld.com/wp-content/uploads/2019/08/aecaf2c6-e6a4-4ad5-a4ad-ebd4f3b6f96e.jpg"),
                "KEYBOARD_SWITCH", "KEYBOARD", "KeyboardShop", InterestLevelEnum.BEGINNER.getId()));

        r.add(new ItemRequest("Tecsee Sapphire V2", 6000L, "Tecsee Sapphire V2", 999,
                Collections.singletonList("https://cdn.shopify.com/s/files/1/0275/3649/0561/products/tecsee-sapphire-v2-tactile-switches-907344_1024x1024.jpg?v=1631230596"),
                "KEYBOARD_SWITCH", "KEYBOARD", "KeyboardShop", InterestLevelEnum.BEGINNER.getId()));

        r.add(new ItemRequest("Durock L1", 8000L, "Durock Linear L1 60 gram actuation forces", 999,
                Arrays.asList("https://cdn.shopify.com/s/files/1/0573/7339/3094/products/1_e30e589f-4d9d-484d-a02a-a52709a76213_grande.png?v=1623956493",
                        "https://cdn.shopify.com/s/files/1/0573/7339/3094/products/3_a2318fe4-fafe-4ade-8301-78e0eca4fbda_grande.png?v=1623956493"),
                "KEYBOARD_SWITCH", "KEYBOARD", "KeyboardShop", InterestLevelEnum.ENTHUSIAST.getId()));

        // KEYCAPS
        r.add(new ItemRequest("Redragon PBT Pudding Keycaps", 135000L, "Mechanical Keyboard PBT Pudding Keycaps Redragon SCARAB A130 Keycaps", 3,
                Collections.singletonList("https://images.tokopedia.net/img/cache/500-square/hDjmkQ/2022/10/13/d30c3ab3-82a8-4498-909a-6fe7cce6ecde.jpg.webp?ect=4g"),
                "KEYCAPS", "KEYBOARD", "KeyboardShop", InterestLevelEnum.BEGINNER.getId()));

        r.add(new ItemRequest("Akko Midnight", 550000L, "AKKO PBT Midnight Keycaps with ASA Profile", 10,
                Collections.singletonList("https://cdn.shopify.com/s/files/1/0563/2154/0245/products/product-image-1821137548_2048x2048.jpg"),
                "KEYCAPS", "KEYBOARD", "KeyboardShop", InterestLevelEnum.INTERMEDIATE.getId()));

        r.add(new ItemRequest("Akko WOB", 585000L, "AKKO PBT WOB (White on Black) Keycaps with ASA Profile", 1,
                Collections.singletonList("https://cdn-amz.woka.io/images/I/51p2kNue1cL.jpg"),
                "KEYCAPS", "KEYBOARD", "KeyboardShop", InterestLevelEnum.INTERMEDIATE.getId()));

        r.add(new ItemRequest("GMK WOB", 1280000L, "GMK Cherry profile white on black color scheme", 29,
                Collections.singletonList("https://media.karousell.com/media/photos/products/2019/04/24/gmk_massdrop_white_on_black_double_shot_abs_keycaps_1556096616_f699b8e0_progressive.jpg"),
                "KEYCAPS", "KEYBOARD", "KeyboardShop", InterestLevelEnum.INTERMEDIATE.getId()));

        // DIY_KIT
        r.add(new ItemRequest("Epomaker GK68X Keyboard kit", 520000L, "Epomaker GK68X 65% beginner keyboard kit", 12,
                Arrays.asList("https://m.media-amazon.com/images/I/61PpEjgXBLL._SL1000_.jpg",
                        "https://m.media-amazon.com/images/S/aplus-media/sc/fafddea8-72e6-4fe4-8c56-de20c47505a8.__CR0,0,970,600_PT0_SX970_V1___.jpg",
                        "https://cdn.shopify.com/s/files/1/0280/3931/5529/products/20200806185358_2048x2048.jpg?v=1596711273"),
                "DIY_KIT", "KEYBOARD", "KeyboardShop", InterestLevelEnum.BEGINNER.getId()));

        // ComputerShop
        // COMPUTER
        // CPU
        r.add(new ItemRequest("AMD Ryzen 5 3600", 3200000L, "AMD Ryzen 5 3600, 6 Core 12 Threads", 29,
                Arrays.asList("https://cdn.wccftech.com/wp-content/uploads/2016/12/AMD-RYZEN-ZEN.jpg",
                        "https://www.bhphotovideo.com/images/images1000x1000/amd_100_100000031box_ryzen_5_3600_3_6_1485462.jpg"),
                "CPU", "COMPUTER", "ComputerShop", InterestLevelEnum.INTERMEDIATE.getId()));

        r.add(new ItemRequest("Intel i7 12700k", 5200000L, "Intel i7 12700k, unlocked processor from intel, base clock 3.6 Ghz", 29,
                Arrays.asList("https://i.pcmag.com/imagery/reviews/07rfvBq3YYV4bfaooOD3INP-4.fit_lim.size_768x.jpg",
                        "https://i.pcmag.com/imagery/reviews/07rfvBq3YYV4bfaooOD3INP-1.fit_scale.size_1028x578.v1640904828.jpg"),
                "CPU", "COMPUTER", "ComputerShop", InterestLevelEnum.ENTHUSIAST.getId()));

        r.add(new ItemRequest("AMD Ryzen 7 5800X3D", 5400000L, "Get an average of 15% more performance with AMD Ryzen™ 7 5800X3D, the only processor with AMD 3D V-Cache™ technology.", 11,
                Arrays.asList("https://images.saymedia-content.com/.image/t_share/MTk3NDM0MjI2NjE2OTAyNjYy/amd-ryzen-7-5800x3d-review.jpg",
                        "https://i.gadgets360cdn.com/large/amd_ryzen_7_5800x3d_image_1647415623992.jpg"),
                "CPU", "COMPUTER", "ComputerShop", InterestLevelEnum.ENTHUSIAST.getId()));

        // GPU

        // RAM
        r.add(new ItemRequest("Gskill Trident Z RGB DDR4 PC22880 2 x 8GB", 1180000L, "Memory Gskill Trident Z RGB DDR4 PC28800 3600Mhz 16GB 2x8GB Ram", 190,
                Arrays.asList("https://cdn.wccftech.com/wp-content/uploads/2017/11/01.-Trident-Z-RGB.jpg",
                        "https://c1.neweggimages.com/ProductImageCompressAll1280/20-232-819-V04.jpg"),
                "CPU", "COMPUTER", "ComputerShop", InterestLevelEnum.ENTHUSIAST.getId()));

        r.add(new ItemRequest("ADATA XPG SPECTRIX D50 DDR4 16GB", 900000L, "ADATA XPG SPECTRIX D50 DDR4 16GB (2x8GB) RGB 3600MHz - WHITE", 190,
                Arrays.asList("https://http2.mlstatic.com/D_NQ_NP_2X_612029-MLM43449082677_092020-F.jpg",
                        "https://images.tokopedia.net/img/cache/500-square/VqbcmM/2020/11/3/d9cdc4b7-2d49-418d-b935-396c9e52497c.jpg?ect=4g"),
                "CPU", "COMPUTER", "ComputerShop", InterestLevelEnum.ENTHUSIAST.getId()));

        // MOTHERBOARD

        // GuitarShop
        // MUSIC
        // GUITAR

        r.forEach(this::insert);
    }

    private void insert(ItemRequest request) {
        ItemDO itemDO = convertFromRequest(request);
        itemDO.setPostId(createPostForItem(request, request.merchantId));

        try {
            itemRepository.save(itemDO);
        } catch (Exception e) {
            LogUtil.exception(e.getMessage(), e);
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    private String createPostForItem(ItemRequest request, String userId) {
        List<String> tags = new ArrayList<>();
        tags.add("item");

        PostVO postVO = new PostVO();
        postVO.setTitle(request.itemName);
        postVO.setContent(request.itemDescription);
        postVO.setUserId(userId);
        postVO.setTags(tags);


        return postService.create(postVO);
    }

    private ItemDO convertFromRequest(ItemRequest request) {

        List<String> images = new ArrayList<>();
        request.imageUrl.forEach(url -> images.add(downloadImage(url)));

        StringBuilder sb = new StringBuilder();
        images.forEach(image -> sb.append(image).append(CommonConst.SEPARATOR));

        String imageStr = sb.substring(0, sb.length() - 1);

        ItemDO item = new ItemDO();
        item.setItemId(FunctionUtil.generateUUID());
        item.setItemName(request.itemName);
        item.setItemPrice(request.itemPrice);
        item.setItemDescription(request.itemDescription);
        item.setItemQuantity(request.itemQuantity);
        item.setItemImages(imageStr);
        item.setCategoryId(request.categoryId);
        item.setHobbyId(request.hobbyId);
        item.setMerchantId(request.merchantId);
        item.setMerchantLevelId(request.merchantLevelId);
        item.setReview(0.0);
        item.setDeleted(false);
        item.setApproved(true);

        return item;
    }

    private final class ItemRequest {
        String itemName;
        Long itemPrice;
        String itemDescription;
        Integer itemQuantity;
        List<String> imageUrl;
        String categoryId;
        String hobbyId;
        String merchantId;
        String merchantLevelId;

        public ItemRequest(String itemName, Long itemPrice, String itemDescription, Integer itemQuantity, List<String> imageUrl, String categoryId, String hobbyId, String merchantId, String merchantLevelId) {
            this.itemName = itemName;
            this.itemPrice = itemPrice;
            this.itemDescription = itemDescription;
            this.itemQuantity = itemQuantity;
            this.imageUrl = imageUrl;
            this.categoryId = categoryId;
            this.hobbyId = hobbyId;
            this.merchantId = merchantId;
            this.merchantLevelId = merchantLevelId;
        }
    }
}
