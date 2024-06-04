package pract.oop_java.pms.v1.fileHandling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import pract.oop_java.pms.v1.audits.Initializer;
import pract.oop_java.pms.v1.enums.EFileSizeType;
import pract.oop_java.pms.v1.enums.EFileStatus;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "files", uniqueConstraints = {@UniqueConstraint(columnNames = "path")})
public class File extends Initializer {
    @Id
    @GeneratedValue(generator = "fileUUID")
    @GenericGenerator(name = "fileUUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

    @Transient
    private String url;

    @Column(name = "size")
    private int size;

    @Column(name = "size_type")
    @Enumerated(EnumType.STRING)
    private EFileSizeType sizeType;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EFileStatus status;

    public File(String directory, String fileName, String extension, String fileBaseName) {
        super();
    }

    public String getUrl() {
        return "/files/load-file/" + name;
    }
}
