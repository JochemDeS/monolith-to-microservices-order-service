package com.example.oderservice.application.common;

public interface UseCase<T, R> {
    R handle (T request);
}
