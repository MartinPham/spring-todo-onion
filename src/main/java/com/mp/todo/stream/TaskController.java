package com.mp.todo.stream;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.time.Duration;
import java.util.UUID;

@RestController("stream.task")
public class TaskController {
    @GetMapping(value = "/stream/task", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> index() {
        return Flux.generate((SynchronousSink<String> sink) -> sink.next(UUID.randomUUID().toString()))
                .delayElements(Duration.ofSeconds(1L));
    }
}
