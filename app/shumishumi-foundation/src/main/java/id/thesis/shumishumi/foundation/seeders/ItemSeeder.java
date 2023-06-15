package id.thesis.shumishumi.foundation.seeders;

import id.thesis.shumishumi.common.service.PostService;
import id.thesis.shumishumi.common.util.FunctionUtil;
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
                "RAM", "COMPUTER", "ComputerShop", InterestLevelEnum.ENTHUSIAST.getId()));

        r.add(new ItemRequest("ADATA XPG SPECTRIX D50 DDR4 16GB", 900000L, "ADATA XPG SPECTRIX D50 DDR4 16GB (2x8GB) RGB 3600MHz - WHITE", 190,
                Arrays.asList("https://webapi3.adata.com/storage/product/d50_pk_dual_gr_1000x1000.png",
                        "https://images.tokopedia.net/img/cache/500-square/VqbcmM/2020/11/3/d9cdc4b7-2d49-418d-b935-396c9e52497c.jpg?ect=4g"),
                "RAM", "COMPUTER", "ComputerShop", InterestLevelEnum.ENTHUSIAST.getId()));


        r.add(new ItemRequest("YAMAHA Electric Guitar", 3500000L, "YAMAHA Electric Guitar for hobbyist", 810
                , Arrays.asList("https://m.media-amazon.com/images/I/51XwbPA5mLL._AC_SL1000_.jpg")
                , "ELECTRIC_GUITAR", "MUSIC", "GuitarShop", InterestLevelEnum.INTERMEDIATE.getId()));
        r.add(new ItemRequest("MSI RX 6800 XT", 8250000L, "MSI Gaming AMD Radeon RX 6800 XT 16GB GDRR6 256-bit HDMI/DP 2310 MHz Boost Clock RDNA 2 OC Graphics Card (RX 6800 XT Gaming Z Trio 16G) ", 487
                , Arrays.asList("https://m.media-amazon.com/images/I/71P4+eYukZL._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/813dY1jg2jS._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/815a7kn-FDS._AC_SL1500_.jpg")
                , "GPU", "COMPUTER", "ComputerShop", InterestLevelEnum.ENTHUSIAST.getId()));
        r.add(new ItemRequest("MSI RTX 2080 Super", 11500000L, "MSI Gaming GeForce RTX 2080 SUPER", 950
                , Arrays.asList("https://m.media-amazon.com/images/I/61M1b4J-mkL._AC_SL1024_.jpg",
                "https://m.media-amazon.com/images/I/61DMLJXz+dL._AC_SL1024_.jpg")
                , "GPU", "COMPUTER", "ComputerShop", InterestLevelEnum.ENTHUSIAST.getId()));
        r.add(new ItemRequest("ZOTAC RTX 3060 TI Twin Edge", 6500000L, "ZOTAC Gaming Geforce RTX 3070 TI Twin Edge OC LHR 8GB", 11
                , Arrays.asList("https://m.media-amazon.com/images/I/81iKGq2f77L._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/71xyt0Kzu0L._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/817lx4THxIL._AC_SL1500_.jpg")
                , "GPU", "COMPUTER", "ComputerShop", InterestLevelEnum.INTERMEDIATE.getId()));
        r.add(new ItemRequest("Joymusic 38 Acoustic Guitar", 750000L, "Joymusic 38 inch blueburst beginner acoustic guitar kit, bundle with a strap with picks holder, digital tuner, set string, and etc", 150
                , Arrays.asList("https://m.media-amazon.com/images/I/81FYp+X68mL._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/8115Y3esipL._AC_SL1500_.jpg")
                , "ACOUSTIC_GUITAR", "MUSIC", "GuitarShop", InterestLevelEnum.BEGINNER.getId()));
        r.add(new ItemRequest("YMC 38 Acoustic Guitar", 850000L, "YMC 38 Coffee Acoustic Guitar setarted package for student", 881
                , Arrays.asList("https://m.media-amazon.com/images/I/71RnLkDoX+L._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/71S-QYOtr4L._AC_SL1500_.jpg")
                , "ACOUSTIC_GUITAR", "MUSIC", "GuitarShop", InterestLevelEnum.BEGINNER.getId()));
        r.add(new ItemRequest("Taylor BT2 Acoustic Guitar", 6750000L, "Taylor BT2 Baby Taylor Acoustic Guitar, Mahogany Top ", 319
                , Arrays.asList("https://m.media-amazon.com/images/I/51Z8tErVBuL._AC_.jpg")
                , "ACOUSTIC_GUITAR", "MUSIC", "GuitarShop", InterestLevelEnum.ENTHUSIAST.getId()));
        r.add(new ItemRequest("ASUS TUF Gaming A520M-PLUS", 1250000L, "ASUS TUF Gaming A520M-PLUS (WiFi) AMD AM4 (3rd Gen Ryzen?) microATX Gaming Motherboard (M.2 Support, 802.11ac Wi-Fi, DisplayPort, HDMI, D-Sub, USB 3.2 Gen 1 Type-A and Aura Addressable Gen 2 headers) ", 90
                , Arrays.asList("https://m.media-amazon.com/images/I/714UqSaJ8AL._AC_SL1280_.jpg",
                "https://m.media-amazon.com/images/I/71eWVmCYVAL._AC_SL1280_.jpg")
                , "MOTHERBOARD", "COMPUTER", "ComputerShop", InterestLevelEnum.BEGINNER.getId()));
        r.add(new ItemRequest("ASUS Prime A520M-A II/CSM", 950000L, "ASUS Prime A520M-A II/CSM AMD AM4(3rd Gen Ryzen) microATX Commercial Motherboard(ECC Memory,M.2 Support,1Gb Ethernet, DP/HDMI 2.1/D-Sub, 4K@60HZ, USB3.2 Gen1Type-A, ARGBheader with AURAsync) ", 671
                , Arrays.asList("https://m.media-amazon.com/images/I/81a1KXIkFmL._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/61ilZFBR1lL._AC_SL1500_.jpg")
                , "MOTHERBOARD", "COMPUTER", "ComputerShop", InterestLevelEnum.BEGINNER.getId()));
        r.add(new ItemRequest("MSI MAG B550 TOMAHAWK", 2250000L, "MSI MAG B550 TOMAHAWK Gaming Motherboard (AMD AM4, DDR4, PCIe 4.0, SATA 6Gb/s, M.2, USB 3.2 Gen 2, HDMI/DP, ATX, AMD Ryzen 5000 Series processors) ", 850
                , Arrays.asList("https://m.media-amazon.com/images/I/910jyKG9QlL._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/81YUAXq1I1L._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/81sRLP5ZFgL._AC_SL1500_.jpg")
                , "MOTHERBOARD", "COMPUTER", "ComputerShop", InterestLevelEnum.INTERMEDIATE.getId()));
        r.add(new ItemRequest("ASUS Prime B760-PLUS D4", 1850000L, "ASUS Prime B760-PLUS D4 Intel (13th and 12th Gen)LGA 1700 ATX motherboard PCIe 5.0,3xPCIe 4.0 M.2 slots,DDR4,2.5Gb LAN,DisplayPort,USB 3.2 Gen 2x2 Type-C,front USB 3.2 Gen 1 Type-C,Thunderbolt (USB4?) ", 754
                , Arrays.asList("https://m.media-amazon.com/images/I/81q9RpcrWfL._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/71KDEEfostL._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/8140-bn-5iL._AC_SL1500_.jpg")
                , "MOTHERBOARD", "COMPUTER", "ComputerShop", InterestLevelEnum.INTERMEDIATE.getId()));
        r.add(new ItemRequest("GIGABYTE Z790 AORUS Elite", 3550000L, "GIGABYTE Z790 AORUS Elite AX (LGA 1700/ Intel Z790/ ATX/ DDR5/ Quad M.2/ PCIe 5.0/ USB 3.2 Gen2X2 Type-C/Intel WiFi 6E/ 2.5GbE LAN/Q-Flash Plus/PCIe EZ-Latch/Gaming Motherboard) ", 300
                , Arrays.asList("https://m.media-amazon.com/images/I/81a48Z1GciL._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/817FcUYRBaL._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/816uhtGuNYL._AC_SL1500_.jpg")
                , "MOTHERBOARD", "COMPUTER", "ComputerShop", InterestLevelEnum.ENTHUSIAST.getId()));
        r.add(new ItemRequest("ASUS ROG Maximus Z790 HERO", 7850000L, "ASUS ROG Maximus Z790 Hero (WiFi 6E) LGA 1700(Intel?13th&12th Gen) ATX Gaming Motherboard(PCIe 5.0,DDR5,20+1power Stages,2.5Gb LAN, Bluetooth V5.2,2X Thunderbolt 4 Ports,5xM.2, Thunderbolt? 4/USB4) ", 363
                , Arrays.asList("https://m.media-amazon.com/images/I/81CpgF-+P4L._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/81a4Zdfbs+L._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/91QGOTGY+eL._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/91NRdqutJbL._AC_SL1500_.jpg")
                , "MOTHERBOARD", "COMPUTER", "ComputerShop", InterestLevelEnum.ENTHUSIAST.getId()));
        r.add(new ItemRequest("Hamilton Coffee Grinder", 250000L, "Hamilton Beach Fresh Grind Electric Coffee Grinder for Beans, Spices and More, Stainless Steel Blades, Removable Chamber, Makes up to 12 Cups, Black ", 273
                , Arrays.asList("https://m.media-amazon.com/images/I/61sjgf+eF7L._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/71v6gUBjZcL._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/71IgZoHOzsL._AC_SL1500_.jpg")
                , "COFFEE_GRINDER", "COFFEE", "CoffeeShop", InterestLevelEnum.BEGINNER.getId()));
        r.add(new ItemRequest("Black+Decker Grinder", 250000L, "Black+Decker Grinder One Touch Push-Button Control, 2/3 Cup Coffee Bean Capacity, Stainless Steel ", 273
                , Arrays.asList("https://m.media-amazon.com/images/I/719RWIc4TzL._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/91xCoHTDY+L._AC_SL1500_.jpg")
                , "COFFEE_GRINDER", "COFFEE", "CoffeeShop", InterestLevelEnum.BEGINNER.getId()));
        r.add(new ItemRequest("Mr. Coffee Simple Grinder", 350000L, " Mr. Coffee Simple Grind 14 Cup Coffee Grinder, Black ", 372
                , Arrays.asList("https://m.media-amazon.com/images/I/71gJtJRL7UL._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/81b0EYwkX9L._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/81slqUsWVPL._AC_SL1500_.jpg")
                , "COFFEE_GRINDER", "COFFEE", "CoffeeShop", InterestLevelEnum.BEGINNER.getId()));
        r.add(new ItemRequest("OXO Brew Conical Burr Coffee Grinder , Silver ", 1000000L, "The OXO Brew Conical Burr Coffee Grinder helps your beans unlock their full potential. The design is simple, so nothing stands between you and your first cup. Just add beans and choose from 15 grind size settings - from fine for espresso to coarse for French press - then turn the dial to select grinding time and push to start. Stainless-steel conical burrs create uniform grounds, for gold-cup flavor, and the one-touch timer keeps your last setting, so you don't have to reset it every time. The Grinder holds up to 12 oz. of coffee beans in a UV-blocking hopper so your coffee tastes fresh.", 993
                , Arrays.asList("https://m.media-amazon.com/images/I/71DeUg8G7kS._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/61HdQa0i2wL._AC_SL1001_.jpg",
                "https://m.media-amazon.com/images/I/61r9h4gua2L._AC_SL1001_.jpg")
                , "COFFEE_GRINDER", "COFFEE", "CoffeeShop", InterestLevelEnum.ENTHUSIAST.getId()));
        r.add(new ItemRequest("Cuisinart Coffee Grinder", 850000L, "Cuisinart Coffee Grinder, Electric Burr One-Touch Automatic Grinder with 18-Position Grind Selector, Stainless Steel, DBM-8P1 ", 621
                , Arrays.asList("https://m.media-amazon.com/images/I/61FO+YCV+eL._AC_SL1175_.jpg")
                , "COFFEE_GRINDER", "COFFEE", "CoffeeShop", InterestLevelEnum.ENTHUSIAST.getId()));
        r.add(new ItemRequest("Hamilton Beach Electric Coffee Grinder", 550000L, "Hamilton Beach Electric Coffee Grinder for Beans, Spices and More, with Multiple Grind Settings for up to 14 Cups, Removable Stainless Steel Chamber, Grey (80396C) ", 201
                , Arrays.asList("https://m.media-amazon.com/images/I/615cmcKp11L._AC_SL1500_.jpg")
                , "COFFEE_GRINDER", "COFFEE", "CoffeeShop", InterestLevelEnum.INTERMEDIATE.getId()));
        r.add(new ItemRequest("Mueller SuperGrind Burr Coffee Grinder", 450000L, "Mueller SuperGrind Burr Coffee Grinder Electric with Removable Burr Grinder Part - 12 Cups of Coffee, 17 Grind Settings with 5,8oz/164g Coffee Bean Hopper Capacity, Matte Black ", 183
                , Arrays.asList("https://m.media-amazon.com/images/I/81jP1Fn+mEL._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/71WemH3mVfL._AC_SL1500_.jpg")
                , "COFFEE_GRINDER", "COFFEE", "CoffeeShop", InterestLevelEnum.INTERMEDIATE.getId()));
        r.add(new ItemRequest("Coffee Gator Pour Over Coffee Maker", 250000L, " Coffee Gator Pour Over Coffee Maker - 14 oz Paperless, Portable, Drip Coffee Brewer Pour Over Set w/Glass Carafe & Stainless-Steel Mesh Filter, Clear ", 509
                , Arrays.asList("https://m.media-amazon.com/images/I/81pVqOS2f7L._AC_SL1500_.jpg")
                , "COFFEE_BREWER", "COFFEE", "CoffeeShop", InterestLevelEnum.BEGINNER.getId()));
        r.add(new ItemRequest("Elite Gourmet CCM040 Stainless Steel 40 Cup Coffee Urn ", 700000L, "Elite Gourmet CCM040 Stainless Steel 40 Cup Coffee Urn Removable Filter For Easy Cleanup, Two Way Dispenser with Cool-Touch Handles Electric Coffee Maker Urn, Stainless Steel, BREWS ENOUGH FOR A CROWD: Makes 20 to 40 cups of hot, fresh coffee, perfect for social gatherings and buffet style meals. Fast brewing in minutes, makes about 1-cup per minute. ", 228
                , Arrays.asList("https://m.media-amazon.com/images/I/612adceJb8L._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/71DzPvGWGvL._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/81gIF5lSO0L._AC_SL1500_.jpg")
                , "COFFEE_BREWER", "COFFEE", "CoffeeShop", InterestLevelEnum.INTERMEDIATE.getId()));
        r.add(new ItemRequest("Ninja CE251 Programmable Brewer", 700000L, "Full flavor from small batch to carafe, Ninja Coffee Brewer is a 12-cup programmable coffee maker with Classic and Rich strength settings that makes hot, flavourful coffee that's never bitter", 468
                , Arrays.asList("https://m.media-amazon.com/images/I/81+HcYgDo1L._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/81mEMIWDZOL._AC_SL1500_.jpg",
                "https://m.media-amazon.com/images/I/81bKTshoy4L._AC_SL1500_.jpg")
                , "COFFEE_BREWER", "COFFEE", "CoffeeShop", InterestLevelEnum.INTERMEDIATE.getId()));

        r.forEach(this::insert);
    }

    private void insert(ItemRequest request) {
        ItemDO itemDO = convertFromRequest(request);
        itemDO.setPostId(createPostForItem(request, request.merchantId));

        try {
            itemRepository.save(itemDO);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
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
