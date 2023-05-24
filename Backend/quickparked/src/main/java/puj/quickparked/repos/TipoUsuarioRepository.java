package puj.quickparked.repos;

import puj.quickparked.domain.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer> {
}
