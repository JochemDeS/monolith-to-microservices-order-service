package com.example.oderservice.domain;


public record User(
        UserId id,
        String username,
        String token,
        String email,
        Name name
) {
    public User(final Builder builder) {
        this(builder.id, builder.username, builder.token, builder.email, builder.name);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UserId id;
        private String username;
        private String token;
        private String email;
        private Name name;

        public Builder id(UserId id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder name(Name name) {
            this.name = name;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
