package com.rapid.swifta.Entities;

import com.rapid.swifta.Enums.EnumAttachmentPurpose;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table
@Entity
@Builder

@AllArgsConstructor
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attachmentId;

    @Column
    private String attachmentName;

    @Column
    private Integer createdBy;

    @Column
    private Integer serviceId;

    @Column(columnDefinition = "LONGBLOB")
    private String attachmentType;

    @Lob
    @Column(name = "attachmentdata",length = 1000,columnDefinition = "LONGBLOB")
    private byte[] attachmentData;

    @Column
    private String mimeType;

    @Column
    private String docType;

    @Column
    private EnumAttachmentPurpose attachmentPurpose;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Attachment that = (Attachment) o;
        return getAttachmentId() != null && Objects.equals(getAttachmentId(), that.getAttachmentId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
