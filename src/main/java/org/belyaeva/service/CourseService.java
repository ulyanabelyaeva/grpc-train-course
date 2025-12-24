package org.belyaeva.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.belyaeva.proto.CourseProto;
import org.belyaeva.proto.CourseServiceGrpc;
import org.slf4j.Logger;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@GrpcService
public class CourseService extends CourseServiceGrpc.CourseServiceImplBase {

    private static final Logger LOGGER = getLogger(CourseService.class);

    @Override
    public void getCursesByStudentId(CourseProto.StudentId request,
                                     StreamObserver<CourseProto.Courses> responseObserver) {
        LOGGER.info("Received request of student courses {}", request);
        CourseProto.Course course1 = CourseProto.Course.newBuilder()
                .setId(1L)
                .setName("Mathematical analysis")
                .setDurationInHours(200)
                .setYearOfEducation(1)
                .build();
        CourseProto.Course course2 = CourseProto.Course.newBuilder()
                .setId(2L)
                .setName("C++ programming")
                .setDurationInHours(200)
                .setYearOfEducation(1)
                .build();
        CourseProto.Courses courses = CourseProto.Courses.newBuilder()
                .addAllCourses(List.of(course1, course2))
                .build();
        responseObserver.onNext(courses);
        responseObserver.onCompleted();
    }
}
