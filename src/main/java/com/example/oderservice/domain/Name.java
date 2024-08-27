package com.example.oderservice.domain;

public record Name(String firstName, String lastName) {

    private Name(Builder builder) {
        this(builder.firstName, builder.lastName);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String firstName;
        private String lastName;

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Name build() {
            return new Name(this);
        }
    }
}
