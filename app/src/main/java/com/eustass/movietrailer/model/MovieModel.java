package com.eustass.movietrailer.model;

import java.util.List;

public class MovieModel {

    private Integer total_results;
    private List<Results> results;

    public Integer getTotal_results() {
        return total_results;
    }

    public void setTotal_results(Integer total_results) {
        this.total_results = total_results;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public class Results {

        private String backdrop_path;
        private String poster_path;
        private Integer id;
        private String overview;
        private String release_date;
        private String title;

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public void setBackdrop_path(String backdrop_path) {
            this.backdrop_path = backdrop_path;
        }

        public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public Integer getId() {

            return id;
        }

        public void setId(Integer id) {

            this.id = id;
        }

        public String getOverview() {

            return overview;
        }

        public void setOverview(String overview) {

            this.overview = overview;
        }

        public String getRelease_date() {

            return release_date;
        }

        public void setRelease_date(String release_date) {

            this.release_date = release_date;
        }

        public String getTitle() {

            return title;
        }

        public void setTitle(String title) {

            this.title = title;
        }

        @Override
        public String toString() {
            return "Results{" +
                    "backdrop_path='" + backdrop_path + '\'' +
                    ", id=" + id +
                    ", overview='" + overview + '\'' +
                    ", release_date='" + release_date + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

}
