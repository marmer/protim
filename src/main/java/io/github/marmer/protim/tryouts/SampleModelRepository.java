package io.github.marmer.protim.tryouts;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.marmer.protim.model.SampleModel;

public interface SampleModelRepository extends JpaRepository<SampleModel, Long> {

}
