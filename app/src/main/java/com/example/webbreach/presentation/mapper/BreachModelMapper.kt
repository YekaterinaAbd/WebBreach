package com.example.webbreach.presentation.mapper

import com.example.webbreach.domain.Mapper
import com.example.webbreach.domain.model.Breach
import com.example.webbreach.presentation.model.BreachModel

class BreachModelMapper : Mapper<BreachModel, Breach> {
    override fun from(model: BreachModel): Breach = with(model) {
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
            isVerified = null,
            isFabricated = null,
            isSensitive = null,
            isRetired = null,
            isSpamList = null
        )
    }

    override fun to(model: Breach): BreachModel = with(model) {
        return BreachModel(
            name = name,
            title = title,
            domain = domain,
            breachDate = breachDate,
            addedDate = addedDate,
            modifiedDate = modifiedDate,
            pwnCount = pwnCount,
            description = description,
            logoPath = logoPath,
            dataClasses = dataClasses
        )
    }
}