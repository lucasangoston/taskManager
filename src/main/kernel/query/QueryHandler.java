package main.kernel.query;

public interface QueryHandler<Q extends Query, R> {
    R handle(Q query);
}
