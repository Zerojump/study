package com.cmy.dto;

import java.util.List;
import java.util.Set;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2018/3/4
 */
public class MusicDto {
    private Integer mid;
    private String title;
    private SourceDto source;
    private List<String> tags;

    public MusicDto(Integer mid, String title, SourceDto source, List<String> tags) {
        this.mid = mid;
        this.title = title;
        this.source = source;
        this.tags = tags;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SourceDto getSource() {
        return source;
    }

    public void setSource(SourceDto source) {
        this.source = source;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "MusicDto{" +
                "mid=" + mid +
                ", title='" + title + '\'' +
                ", source=" + source +
                ", tags=" + tags +
                '}';
    }

    public static class SourceDto {
        private Integer sid;
        private String source;

        public SourceDto(Integer sid, String source) {
            this.sid = sid;
            this.source = source;
        }

        public Integer getSid() {
            return sid;
        }

        public void setSid(Integer sid) {
            this.sid = sid;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        @Override
        public String toString() {
            return "SourceDto{" +
                    "sid=" + sid +
                    ", source='" + source + '\'' +
                    '}';
        }
    }
}
