package com.example.webbreach.data.mapper

import com.example.webbreach.data.model.BreachData
import com.example.webbreach.domain.Mapper
import com.example.webbreach.domain.model.Breach

class BreachDataMapper : Mapper<BreachData, Breach> {
    override fun from(model: BreachData): Breach = with(model) {
        return Breach(
            name = name,
            title = title,
            domain = domain,
            breachDate = breachDate,
            addedDate = addedDate,
            modifiedDate = modifiedDate,
            pwnCount = pwnCount,
            description = description,
            logoPath = logoPath,
            dataClasses = dataClasses,
            isVerified = isVerified,
            isFabricated = isFabricated,
            isSensitive = isSensitive,
            isRetired = isRetired,
            isSpamList = isSpamList
        )
    }

    override fun to(model: Breach): BreachData = with(model) {
        return BreachData(
            name = name,
            title = title,
            domain = domain,
            breachDate = breachDate,
            addedDate = addedDate,
            modifiedDate = modifiedDate,
            pwnCount = pwnCount,
            description = description,
            logoPath = logoPath,
            dataClasses = dataClasses,
            isVerified = isVerified,
            isFabricated = isFabricated,
            isSensitive = isSensitive,
            isRetired = isRetired,
            isSpamList = isSpamList
        )
    }
}