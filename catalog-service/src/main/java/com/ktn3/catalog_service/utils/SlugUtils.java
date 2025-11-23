package com.ktn3.catalog_service.utils;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class SlugUtils {
    private static final Pattern NON_LATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    private SlugUtils() {
        // private constructor để không cho khởi tạo
    }

    /**
     * Chuyển chuỗi thành slug chuẩn SEO-friendly
     * Ví dụ: "Điện Thoại Samsung Galaxy S24 Ultra" → "dien-thoai-samsung-galaxy-s24-ultra"
     */
    public static String toSlug(String input) {
        if (input == null || input.isBlank()) {
            return "";
        }

        // 1. Chuyển tiếng Việt có dấu thành không dấu
        String noAccent = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        // 2. Đổi khoảng trắng thành dấu gạch ngang
        String slug = WHITESPACE.matcher(noAccent).replaceAll("-");

        // 3. Xóa ký tự đặc biệt, giữ lại chữ, số và dấu '-'
        slug = NON_LATIN.matcher(slug).replaceAll("");

        // 4. Viết thường toàn bộ và loại bỏ gạch đầu/cuối
        return slug.toLowerCase(Locale.ROOT).replaceAll("^-+|-+$", "");
    }
}
