package com.example.oderservice.infrastructure.http.user;

public record DetailedUserModel(
        int id,
        String username,
        String email,
        String firstName,
        String lastName
) {
    public DetailedUserModel(Builder builder) {
        this(builder.id, builder.username, builder.email, builder.firstName, builder.lastName);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private int id;
        private String username;
        private String email;
        private String firstName;
        private String lastName;

        private Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public DetailedUserModel build() {
            return new DetailedUserModel(this);
        }
    }
}
