package pl.edu.pk.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 4/12/13
 * Time: 4:31 PM
 */
@Entity
@Table(name = "FILE")
public class File {
    @NotNull
    @Size(min = 1, max = 1000000)
    @Column(name = "FILE_CONTENT", length = 1000000)
    private byte[] content;
    @Length(min = 2, max = 255)
    @Column(name = "FILE_NAME", length = 255, nullable = false)
    private String fileName;
    @Id
    @NotNull
    @GeneratedValue(generator = "FILE_ID_SEQUENCE", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "FILE_ID_SEQUENCE", sequenceName = "FILE_ID_SEQUENCE", initialValue = 1, allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    @OneToOne(cascade = CascadeType.ALL,optional = true)
    @JoinColumn(name = "FILE_ACCESS_ID",nullable = true)
    private FileAccess fileAccess;

    public File() {
    }

    public FileAccess getFileAccess() {
        return fileAccess;
    }

    public void setFileAccess(FileAccess fileAccess) {
        this.fileAccess = fileAccess;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
