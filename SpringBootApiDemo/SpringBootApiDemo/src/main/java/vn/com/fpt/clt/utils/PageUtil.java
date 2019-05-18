package vn.com.fpt.clt.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * 
 * @author ChienNV7
 *
 */

@SuppressWarnings("deprecation")
public class PageUtil {

	
	public static Pageable getPage(Integer page, Integer size) {
		if (page == null || page < 0) {
			page = 0;
		}
		if (size == null || size < 0) {
			size = Integer.MAX_VALUE;
		}
		return new PageRequest(page, size);
	}

	public static Pageable getPage(Integer page, Integer size, Sort sort) {
		if (page == null || page < 0) {
			page = 0;
		}
		if (size == null || size < 0) {
			size = Integer.MAX_VALUE;
		}
		return new PageRequest(page, size, sort);
	}

	public static <U, V> Page<V> copy(Page<U> source, Class<V> target, Pageable pageable) {
		Page<V> pageOfDto = new PageImpl<V>(BeanUtil.copyList(source.getContent(), target), pageable,
				source.getTotalElements());
		return pageOfDto;
	}
}
