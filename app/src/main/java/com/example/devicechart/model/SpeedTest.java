package com.example.devicechart.model;

public class SpeedTest {
    private int downloadRate;
    private int uploadRate;
    private int phyLinkRate;
    private int hitCpuLimit;

    public SpeedTest(int downloadRate, int uploadRate, int phyLinkRate, int hitCpuLimit) {
        this.downloadRate = downloadRate;
        this.uploadRate = uploadRate;
        this.phyLinkRate = phyLinkRate;
        this.hitCpuLimit = hitCpuLimit;
    }

    public int getDownloadRate() {
        return downloadRate;
    }

    public void setDownloadRate(int downloadRate) {
        this.downloadRate = downloadRate;
    }

    public int getUploadRate() {
        return uploadRate;
    }

    public void setUploadRate(int uploadRate) {
        this.uploadRate = uploadRate;
    }

    public int getPhyLinkRate() {
        return phyLinkRate;
    }

    public void setPhyLinkRate(int phyLinkRate) {
        this.phyLinkRate = phyLinkRate;
    }

    public int getHitCpuLimit() {
        return hitCpuLimit;
    }

    public void setHitCpuLimit(int hitCpuLimit) {
        this.hitCpuLimit = hitCpuLimit;
    }

    @Override
    public String toString() {
        return "SpeedTest{" +
                "downloadRate=" + downloadRate +
                ", uploadRate=" + uploadRate +
                ", phyLinkRate=" + phyLinkRate +
                ", hitCpuLimit=" + hitCpuLimit +
                '}';
    }
}
