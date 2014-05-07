package image.editor.model;

public class Status {

    private long processingTime = -1;
    private int threshold = -1;

    public void clear() {
        processingTime = -1;
        threshold = -1;
    }

    public long getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(long processingTime) {
        this.processingTime = processingTime;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

}
