/*
 * Copyright (c) 2022 - present | LuciferMorningstarDev <contact@lucifer-morningstar.dev>
 * Copyright (C) 2022 - present | digitaldojo.tech team and contributors
 * Copyright (C) 2022 - present | Pharaoh & Morningstar LLC team and contributors
 *
 * JTBA - Java Telegram Bot API - Developed with ♥ and Published by digitaldojo.tech
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package tech.digitaldojo.jtba.data.types;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import tech.digitaldojo.jtba.data.Data;
import tech.digitaldojo.jtba.json.JsonSerializer;

import java.util.List;

/**
 * JTBA; tech.digitaldojo.jtba.data.types:EncryptedPassportElement
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @see <a href="https://core.telegram.org/bots/api#encryptedpassportelement">/bots/api#encryptedpassportelement</a>
 * @since 23.11.2022
 */
@EqualsAndHashCode
@AllArgsConstructor
public final class EncryptedPassportElement implements Data {

    /**
     * Element type. One of “personal_details”, “passport”, “driver_license”, “identity_card”, “internal_passport”, “address”, “utility_bill”, “bank_statement”, “rental_agreement”, “passport_registration”, “temporary_registration”, “phone_number”, “email”.
     */
    public final String type;

    /**
     * Optional. Base64-encoded encrypted Telegram Passport element data provided by the user, available for “personal_details”, “passport”, “driver_license”, “identity_card”, “internal_passport” and “address” types. Can be decrypted and verified using the accompanying {@link EncryptedCredentials}.
     */
    public final String data;

    /**
     * Optional. User's verified phone number, available only for “phone_number” type
     */
    public final String phone_number;

    /**
     * Optional. User's verified email address, available only for “email” type
     */
    public final String email;

    /**
     * Optional. Array of encrypted files with documents provided by the user, available for “utility_bill”, “bank_statement”, “rental_agreement”, “passport_registration” and “temporary_registration” types. Files can be decrypted and verified using the accompanying {@link EncryptedCredentials}.
     */
    public final List<PassportFile> files;

    /**
     * Optional. Encrypted file with the front side of the document, provided by the user. Available for “passport”, “driver_license”, “identity_card” and “internal_passport”. The file can be decrypted and verified using the accompanying {@link EncryptedCredentials}.
     */
    public final PassportFile front_side;

    /**
     * Optional. Encrypted file with the reverse side of the document, provided by the user. Available for “driver_license” and “identity_card”. The file can be decrypted and verified using the accompanying {@link EncryptedCredentials}.
     */
    public final PassportFile reverse_side;

    /**
     * Optional. Encrypted file with the selfie of the user holding a document, provided by the user; available for “passport”, “driver_license”, “identity_card” and “internal_passport”. The file can be decrypted and verified using the accompanying {@link EncryptedCredentials}.
     */
    public final PassportFile selfie;

    /**
     * Optional. Array of encrypted files with translated versions of documents provided by the user. Available if requested for “passport”, “driver_license”, “identity_card”, “internal_passport”, “utility_bill”, “bank_statement”, “rental_agreement”, “passport_registration”
     * and “temporary_registration” types. Files can be decrypted and verified using the accompanying {@link EncryptedCredentials}.
     */
    public final List<PassportFile> translation;

    /**
     * Base64-encoded element hash for using in PassportElementErrorUnspecified
     */
    public final String hash;

    /**
     * Creates a new {@link EncryptedPassportElement} instance of a given JSON {@link String}.
     *
     * @param data JSON {@link String}
     * @return {@link EncryptedPassportElement}
     */
    public static EncryptedPassportElement fromJson(String data) {
        return JsonSerializer.fromJson(data, EncryptedPassportElement.class);
    }

    /**
     * Convert a {@link EncryptedPassportElement} instance to a JSON {@link String}
     *
     * @return JSON {@link String}
     */
    @Override
    public String toString() {
        return this.toJson();
    }

}
