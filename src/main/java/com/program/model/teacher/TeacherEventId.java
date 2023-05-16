package com.program.model.teacher;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class TeacherEventId implements Serializable {
    private Long teacherId;
    private Integer eventId;
}
