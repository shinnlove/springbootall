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
            "MIIJQwIBADANBgkqhkiG9w0BAQEFAASCCS0wggkpAgEAAoICAQDGGzMyaFd4gzIR\n" +
            "i8HidguCEtUj7BW88VGtqHsnz0uHhAU0Ybztsa0oYLCIOyxVDCD66Qosnr1g03rb\n" +
            "13RHJDr1V90cEQQ/XUARGF0ZOPdG3ic8Mr45XvMYAiMqeGL4YZe2kZdnWL3ozE59\n" +
            "LDuMEFWphDMNJK4xfZcLF8tipmYuC+LLAnTC6UmQygBek6nGZA8eNBp07C1UsOGK\n" +
            "oEgwopncjIlDSnp29w68B0HcNzDktIX+UgoJ4X/cJDb99KQ+dhgB+JfV5m9f0qRP\n" +
            "+HIf3Lg7XfVPTR0m4MdCwMLEUD0R9Q/6/7Pl2pSBK8J29qyyosqPWhZCGnQlGdAd\n" +
            "Wv73ES9Z+IsmMW17WiziZkyJs2L3mQPETtz0c+uvsCqsMgHnyHGkt4X2PM16nv+N\n" +
            "LzStQ4zeRrErOo7sXb0jp7OYtQ8/nzYFqanPx75mzr1g86DBRbw0OktvxwPUdG6Y\n" +
            "WrnNgGNn/nzXhdh04LKUd+3P4ZW197vCO6ZES9kQMQRH9j6iz1gxJ7+ltmltXEA0\n" +
            "TLedRDpbjMSwzrt4STUFjAz+UcAmDqSqJ2OJ50VNb8A/v8CRbnO3tEpaqdvE03Dn\n" +
            "TUcjEo+vRcWJHUnPTOQk3IOThP4t0K3Qd+ZmLE2gEpkHLfW5fl0HubrA7dM0tbMk\n" +
            "nZ27XvlACH0vNa+31+SmHKg1gr2DFQIDAQABAoICAA+9xo4pU1X8msRhJglQFu8B\n" +
            "fxUpI7sNM8/INOz/YEGG4ID+iSwb86XjNPPGO+oQGijFzqyn+mVQuJDyaEdk3hhz\n" +
            "oFqkEYHVb4tMM82WxLNJYLW8VHgrjsZoGRI9WaPh4VO88tpf50sxvMTeeGbTP3NG\n" +
            "UWYodXzyPUfA3sTacwESqbU23z9DmThbjLb3JcThW4ArZLTItXtmyK0iukm/CdnI\n" +
            "AvopV1zPmcigDQAqFIT/wSvpqemKFQZRjFsWjtx1tselUK9K7augCbVfCPZ2K99v\n" +
            "wlfISVo2/y5TMca2l3uvTNNFcS6A9U7kQsIALM3O+Kh2YJFlF7iA+hPzEJJiN7Bb\n" +
            "DrJI4OqF0ZgLG+HBxFZsqwDo7CqWvMgqT/iU5wGkRQsCnj3D8qgwIvFa19oU+9aF\n" +
            "hEISGX0dB4NdP0H4ftfKI32NcxqHDIGnAsrdixBE67tID+p4vzrHkov/ZGGNXRuu\n" +
            "wN0Z3n+IUfVWXVavuyc50oLrCl1siDuEGRpdNSWPnPPsGvRZ47J7/bcpuXQiYXGj\n" +
            "oyLWHYo29z7nK9w523+2O9zJdgrvQx7Xq0GwexOAs4t0/4tOG2TwUj/1d37rGdgq\n" +
            "c3reyN5Q4pfUFw0xvkYccyTBQR87kVOiD+f9PSV3XPzt/QhS2qQke0q8IPdiR378\n" +
            "arZ4J5EaNURxKJSvXti7AoIBAQD1jTSDbCzR/Sy8GmHq5fKQKtw/pY83UWwZCOWV\n" +
            "3mw4ywTVLBrzB+XNFSM0ah+hFWWm4XFDuBkPkwvhVhosu/PgSpcFdc88H+K0I78O\n" +
            "Fj0We9+QMUJtKBvXhzXYm0/bS3PgyU49/QllWiQFjh/n0S+tzD844V3QJr6wImi8\n" +
            "T1S5qYTYDnXO8IH5XRHLIYPEQygGtYxCsLsn7EOJ6UbGKsIir0mmZVbxPnXExUHo\n" +
            "GpUJnmLGtu6sVhbXcLxKl9kMYKBKgpKA0JrHI5dz5bQ5oedLI/dyH7QxIumveWuq\n" +
            "DRhNfeBK8ez088gzOnGrY/JgRh+KfJoENvCbIuNA3ZKoA5B3AoIBAQDOiSwtH2W/\n" +
            "m77GJaBQ2mn76/oH794G+by9fphIcIngrozTqhdaK6FqQXOzAfXJsz/Twnk6SJhv\n" +
            "fRR35msaF/InH/M6gHJkY1FSJ0rqlV2FZ2ciVmvAXwzoP/epjSC4gPs590kj5ExM\n" +
            "fx7NCl4x3fE8v8QFriQcMiG0LL6LV85BmyFbqyrMmqY70eWNCOtfGTG/+wNaDW0l\n" +
            "idoZzAufW/4mO98GhQAjq0CickKnjpYtR8SkR0hqbRKnNxcjpg2A7E+WbXI8OzWq\n" +
            "Tpbc6QYRKB+/go/gsOsFlfgrJyzyleIuBc3KeDDEWN+ch4jehv12BECSwzZYhwvs\n" +
            "uyRMDAbGM1fTAoIBAERCLnjiF0uAX3EivFl59oXRBEzFDxzHi48iBAA3Rko7VWqU\n" +
            "yiEI7v6lg5XMcmJq+kj7nmq+wGit7b2EG96XmOGemoCwER2bl3+P+EpIME8RvEHv\n" +
            "D5IcdjDhqzw9YGOLg/jWvONKddQwzJamvYPJiMF9Z42+SD/WpWd9CTRfsSOzVfRh\n" +
            "gKo3/Ul+b7XR6TkjmfrTeNwJGN2uNdAegsgPlsvhgk25GgQR4tcCJqII0alM2BR5\n" +
            "QbuyasMU2aK6t2FTcvuDdIpOctgssqtLDWB/+zXOF7knywVluSRIUn+BCGJyPWlX\n" +
            "+xDUu7BR7MtCC1xr4dSzcnT9eMEHX7jmXyBJbV0CggEBAJlzKTNNRJeb653kjrwD\n" +
            "ywK1TMtg2CFuo2BXEmWf15Drnw4ULc185SzA/GlfZ3I4j4+RyegXz8nlsLN0qtZ0\n" +
            "PCH9hwVMMbGMaPsU3oB0AUwAi4vjkaMX+8sC28tJY6oxinaC2ACmulmLiSLmNR3v\n" +
            "vDBaehWOUJ3yUUBAjkGRN1lfHOWywtXJgQCo7lhe7CF34pK0uMTLfPCK/1RzWZ9J\n" +
            "Bgb6LLX3UyctMZSAOC2trr5tuBgmLhOEfntmXvbJhPbonCkzn9BIXZLvWP9A0pUk\n" +
            "rpHnO2bBi5xW1E+bh7WkzqtK/J8VtYXsUhdGS9apjN+GQUPJbjNQAvgcAtfNh6ry\n" +
            "sqkCggEBAMgLtoBpDBh0a/LV7PTnHCr70ofJbp+jnMtV0d+Q78h+Jps2M7mlJqOA\n" +
            "HVsnz8zVrZeVGkzTdaMgIxh1cJKrHpPwY1Im2fAoRNM66fQMLVAGgg6eZho8hIsL\n" +
            "LIithkPn6uQp7/pFRQ0SMn/2hF0NNWb7y4yAw1LVpenHPCo7CxIehe4zurj5c3n8\n" +
            "Lvu3yFz9cWpwtBt305srWo8dVPc6YAwTff8y7qotGuEeuEazcnWpyhGEaymcauGR\n" +
            "xHnvdXetY9YtXu3uLb7bim5nYCRNqjSXyG0LNPNSS5ZT8TefOhnRaxfBOCJgmdez\n" +
            "2qkJHIc89kJXTQQUsnvmHROo5Te7hpM=\n" +
            "-----END PRIVATE KEY-----\n";

}