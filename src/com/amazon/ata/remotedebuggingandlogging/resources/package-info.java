/**
 * Generates coral models.
 */
@CoralGenerate(
    models = @Models("ATACurriculumAlexaWeatherServiceModel"),
    modelValidation = @ModelValidation(ModelValidation.Basic),
    index = @Index,
    types = @Types
)
package com.amazon.ata.remotedebuggingandlogging.resources;

import com.amazon.coral.annotation.generator.CoralGenerate;
import com.amazon.coral.annotation.generator.Index;
import com.amazon.coral.annotation.generator.ModelValidation;
import com.amazon.coral.annotation.generator.Models;
import com.amazon.coral.annotation.generator.Types;
