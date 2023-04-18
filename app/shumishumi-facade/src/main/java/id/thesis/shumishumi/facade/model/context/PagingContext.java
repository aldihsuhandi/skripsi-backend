package id.thesis.shumishumi.facade.model.context;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingContext {
    private Integer pageNumber = 1;
    private Integer numberOfItem = 10;
    private Boolean hasNext = false;
    private Long totalItem;
    private Integer totalPage;

    public PagingContext(Integer pageNumber, Integer numberOfItem, Long totalItem) {
        this.pageNumber = pageNumber;
        this.numberOfItem = numberOfItem;
        this.totalItem = totalItem;
        this.totalPage = this.calculateTotalPage(numberOfItem, totalItem);
        checkHasNext(totalItem, numberOfItem);
    }

    public PagingContext() {
        ;
    }


    public void checkHasNext(long count, int currentItemNumber) {
        int offset = this.calculateOffset() + currentItemNumber;
        this.hasNext = !(count == offset);
    }

    public int calculateTotalPage(int numberOfItem, Long totalItem) {
        if (numberOfItem == 0) {
            return 0;
        }

        int res = (int) (totalItem / (long) numberOfItem);
        if (totalItem % ((long) numberOfItem) != 0) {
            res += 1;
        }

        return res;
    }

    public int calculateOffset() {
        return (this.getPageNumber() - 1) * this.getNumberOfItem();
    }

    @Override
    public String toString() {
        return String.format("PagingContext[pageNumber=%d,numberOfItem=%d,totalItem=%d,totalPage=%d,hasNext=%s,offset=%d]",
                pageNumber, numberOfItem, totalItem, totalPage, hasNext, calculateOffset());
    }
}
