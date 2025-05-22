/**
 * Inc.
 * Copyright (c) 2004-2025 All Rights Reserved.
 */
package com.shinnlove.springbootall.util.algorithm;

/**
 * @author Tony Zhao
 * @version $Id: RSAEncryptConstant.java, v 0.1 2025-05-22 14:24 Tony Zhao Exp $$
 */
public class RSAEncryptConstant {

    public static final String CHARSET_ENCODE = "UTF-8";

    public static final String RSA_ALGORITHM_NAME = "RSA";

    public static final int RSA_ENCODE_BIT = 4096;

    public static final String RSA_PADDING_MODE = "RSA/ECB/PKCS1Padding";

    public static final String SHA_256_RSA_ALGORITHM = "SHA256withRSA";

    public static final String SAMPLE_PRIVATE_KEY_FILE_CONTENT = "-----BEGIN PRIVATE KEY-----\n" +
            "MIIJQQIBADANBgkqhkiG9w0BAQEFAASCCSswggknAgEAAoICAQCr5iIwqG3JEQnQ\n" +
            "6R+T9wFFoirT7/oje+2b1/+wlF5tlHYJdWfQdCk8xknrilsISeTTEtRUw02/untO\n" +
            "ayFu89v/A6jQlbVhxg8k/JZAoF/HI8MsLAX6t1Kl9pmoeuJaJ05K9JlLfnjx6tdu\n" +
            "+4mAUKW1D5dix0ArUPbWw4+QAWHKpoVhjTA25pPrAIJr/69F1+7wQQoyjWAW2ACy\n" +
            "ylC9MzcIQgiiTXukTp4wsxBrX+3lVfjJi5iPb/Sft6MXv3ybK4N+yVkqX08JHknD\n" +
            "u0zAsxWoFdXb6UmoQdHnMYnO3ZuYBrosEDtUJLXEQmksKoCW3ziCwfke/FdhZzAo\n" +
            "ZltgbBYJRIXIMK+WI+hODCTogsu+GiY2F9W/9qlEyfyDNoiuEsXKTkDjoTLp3w5X\n" +
            "UUEQWZ44TUJgwpgk2ItdoGRo/488yrX7oQqtWQqAjZMdrcH0Ff0GRvCFY1R6dNMI\n" +
            "Kwc75w/8ZskymvdWHSeOXDNUfy4WDPZNVd9yUinLTMU/JehPj5Fn3Dc7IJ0gpg9+\n" +
            "494bXDFh2EjOnMy4m2qr3lmFCTLnrGhkrus0f/kyYaC8G+S5XGeZUOd+KWnH6lr8\n" +
            "7fK5KiDM4Hnfr6/hLZMMOw8oLR2ejq5rEZ2zUAPpnAnny9MGd9ZLrUO9sxN7yXPD\n" +
            "RtXs6BoD6KvaVE1bsByDgNSHisy+fwIDAQABAoICAGz8QKsW7lwveEjCtSmrzlum\n" +
            "wH6UxMPgm7pWDR+YQfI50UiX7lUEgOyuT0mN879ikjic1JBVLyMjCXCjjZP5+n5J\n" +
            "hKuSb2hpGUr69gxNYcwxSVnlI3lc54KfwbZHCuHbz9DIGbT6n5XJrgMIaZo4B4ev\n" +
            "Ne0DpRSRhZ6OaKQFrEm+oAxEZtA4CopRTV9JwYeHq08Z/WwlpEl3c6HnkZ8TLI4I\n" +
            "x86XT4/R5Iu9y+g37u2bP1wRLI2sjq0A0d+JoWgEA5/L6sNpoAOI932ob7YxFMb3\n" +
            "6Ro/Y1rROFLbXqMQb8jIYxryw5rKl4+yB1pgLBz7s6l7cAYMOR5hPPAwTTjp7mGx\n" +
            "SEdqCu9ka71wGfRvYAePTp7veF6N78VmTEhDCGPczgBEl5ZoAikch83G0/ofoB9Q\n" +
            "zGB6cz6yHwvQ8kuWLqD2I2b/0nYh2mLqdSaj56m6swaL/hsBxIixS3Muo12yDEgl\n" +
            "alF2TLQ59MPTv1J45UJ1cVH4m/wnNpkT9En1iS28DaWUAVIxK1Nr3L4yzyWWLzTM\n" +
            "B5X67+CwZYwPTSjWKLrTLRagXs7658camL0tVXmcDqEPX0x8FQIXySX89NwIb/bK\n" +
            "FsNtTy0Meweu5rdzKDnJjOngmU+rQ5B3zFCqOVTRm10+5iUmE7jBoWDI0p9fdPIe\n" +
            "krNotkC7lWN6cuRBtM1hAoIBAQDfwjGaQ3taG41TrnQVKQ9FNvXRtAPjbL+4rPya\n" +
            "HHDigQjUktMk9f4IMqTDwpaqJIovw3PvPAzP9uuYf9yHRAARHUJBitHI5PuxOW+p\n" +
            "Kcgy5V0cDnIzmVkH7NSPwvNZwrT2uwJDHkvOYkx+K0wPs9lQ3Iyz3kzh7+X3xk4U\n" +
            "qSSUsGpIb0ca9NaA5NtxvUL7+xQV6nICa3kx7vwXP1FBXaT1Ypkv5wiEmWlK2jMm\n" +
            "6QgEZIJe0Id7GtZt1a2/cpNHeua2EZmW6e9ct7rXcSX6IYw8Mg8eyvZsKs6ou1pv\n" +
            "lj9uiqiqRlmmxRhRPIUTmnQwI+Uo8i85fnLZcJPi52MA23YxAoIBAQDEqv0jHXYu\n" +
            "DW7VlXXX4X7rxDoB7sHuthz+bDPKohsvKRE7uYrj81N9S3U+73IUNTS8Xi7sSg2j\n" +
            "McBIdd+flPjvkMs+ucdKhdyQpGtkNOsWqZEIy72u9ekrXm0puf7RyfEOBF/9RCj9\n" +
            "+VBUlqxn3TBEO0XBl7zFuBqvC1adof8ZZHiDDFJexkjOm7NTY3AqNhm9VrpiVt/s\n" +
            "8oNVHUtWtJ6CgYRppZvplqsmXTTHAUf954ro8cUYD4g7PHfNGC+DaOP0GAKLxcz5\n" +
            "fRuEUWF7POe7XKbIx1aRrQ4XB771xBZd92QIAKZVgc9lyxN6UQ19glGRlGf4hha1\n" +
            "f98U3TlosmOvAoIBAFHbMdg0TaDmKWpujIrdLXftpjnj9/Qv6Wc+6V3bf7Rs7fUO\n" +
            "DW4dC6SmGj5/JizyFxdoEjZ3qlw+AWwVgnB9gOTB5mYhyaMLmcP4tg+VeGaeH9TO\n" +
            "uBqGQ9qxWgWJlQrGPvtv6GDzaVoD/eW+IyIvchWxyUtXzJpu75sYEaJfSkGW1BrR\n" +
            "kpqa7QnULy9JxIJ4FJ372V9nP7vzqIc8o5YdEuat/kpW43fWw30dm87lOwiYWFUl\n" +
            "RhrT85PUsq4x1Q02OcZrY9ugku9BS3nbK8B4m1u+GGf0afeiJV32xBvEREpFMXSx\n" +
            "LcTjn4TAdhJ403508zVqe7o1twAyvfcpHL/IiVECggEAB1xzDx5qLv0XuezXNVZe\n" +
            "XsrAdRQPtDSizy8TnTEGHXTHDVo7hZ608Eqjv9BMj88SAz7vOK7o5cPV86Bd12ZH\n" +
            "Dwh3YwinGfX6tHtC/cQxTG7aW97sYaEYsiPdqCMrE4UgfarSQPONKTxssk3VdCnG\n" +
            "RbeGO+HHEgt7DDQ7cLkAiF3HWPhcGOoJDZqsUqG1MA9IVVrqrQWdagwFuGjZSgyG\n" +
            "plHct+uX811NWKlCcXRt0mYZP/DUtLrYGYeA4G9YT+3cImXHqp2jt27kWcaGBIva\n" +
            "DJxCLd+wMNGuZlRG5IILaEb7OoVztF2geqnoN7rS63C2RuGsJpQ2Q2Le4bQKEw4i\n" +
            "RwKCAQAkwN5zJ6skML3vz/za0c4MKp9pe/BvEo30aTXh8Y+ERr5969oYObJuhxPE\n" +
            "4W1AhmBWmqjtyLh6zM/T7M5fCRsZaPEDVVFZTWRpPHZJdp2tkbb6/HxW9dGzaG/I\n" +
            "bXBs8kv9/+OGsNspPu6cUx6tST1+4umvy0NN8C2yftsKL4YWxiLzOawzrycA0rb1\n" +
            "e/lYvNpXjpnq1FvsyB25ccVMzdpVjVwBHjKXEXleyfTfEvGmbzLuAqpDbguJqBC7\n" +
            "4YJsncjNsLzAf3tfDIYbZVjC7kSYVETtdCvC+KBUpP1/O7HWESDkgMvXhs1M0Xja\n" +
            "RPH9vsCCy0TEFrSzVzUIKga3fZnO\n" +
            "-----END PRIVATE KEY-----\n";

}