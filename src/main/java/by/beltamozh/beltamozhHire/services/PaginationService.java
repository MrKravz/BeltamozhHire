package by.beltamozh.beltamozhHire.services;

import org.springframework.data.domain.Page;

public interface PaginationService<T> {

    Page<T> getPageableList(int page, int size);

    Page<T> getSortedAndPageableList(int page, int size, String fieldName);
}
