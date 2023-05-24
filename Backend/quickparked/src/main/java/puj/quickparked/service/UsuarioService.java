package puj.quickparked.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import puj.quickparked.model.IniciarSesionDTO;
import puj.quickparked.domain.SedeParqueadero;
import puj.quickparked.domain.TipoUsuario;
import puj.quickparked.domain.Usuario;
import puj.quickparked.model.RespuestaIniciarSesionDTO;
import puj.quickparked.model.UsuarioDTO;
import puj.quickparked.repos.SedeParqueaderoRepository;
import puj.quickparked.repos.TipoUsuarioRepository;
import puj.quickparked.repos.UsuarioRepository;
import puj.quickparked.security.jwt.JwtProvider;
import puj.quickparked.security.service.UserDetailsServiceImpl;
import puj.quickparked.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final SedeParqueaderoRepository sedeParqueaderoRepository;
    private final TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    private UserDetailsServiceImpl jwtService;

    @Autowired
    private JwtProvider jwtProvider;

    public UsuarioService(final UsuarioRepository usuarioRepository,
            final SedeParqueaderoRepository sedeParqueaderoRepository,
            final TipoUsuarioRepository tipoUsuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.sedeParqueaderoRepository = sedeParqueaderoRepository;
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public List<UsuarioDTO> findAll() {
        final List<Usuario> usuarios = usuarioRepository.findAll(Sort.by("id"));
        return usuarios.stream()
                .map((usuario) -> mapToDTO(usuario, new UsuarioDTO()))
                .toList();
    }

    public UsuarioDTO get(final Integer id) {
        return usuarioRepository.findById(id)
                .map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final UsuarioDTO usuarioDTO) {
        final Usuario usuario = new Usuario();
        mapToEntity(usuarioDTO, usuario);
        return usuarioRepository.save(usuario).getId();
    }

    public void update(final Integer id, final UsuarioDTO usuarioDTO) {
        final Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(usuarioDTO, usuario);
        usuarioRepository.save(usuario);
    }

    public void delete(final Integer id) {
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO mapToDTO(final Usuario usuario, final UsuarioDTO usuarioDTO) {
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setUsername(usuario.getUsername());
        usuarioDTO.setPassword(usuario.getPassword());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setTelefono(usuario.getTelefono());
        usuarioDTO.setSedeParqueadero(usuario.getSedeParqueadero() == null ? null : usuario.getSedeParqueadero().getId());
        usuarioDTO.setTipoUsuario(usuario.getTipoUsuario() == null ? null : usuario.getTipoUsuario().getId());
        return usuarioDTO;
    }

    private Usuario mapToEntity(final UsuarioDTO usuarioDTO, final Usuario usuario) {
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setNombres(usuarioDTO.getNombres());
        usuario.setApellidos(usuarioDTO.getApellidos());
        final SedeParqueadero sedeParqueadero = usuarioDTO.getSedeParqueadero() == null ? null : sedeParqueaderoRepository.findById(usuarioDTO.getSedeParqueadero())
                .orElseThrow(() -> new NotFoundException("sedeParqueadero not found"));
        usuario.setSedeParqueadero(sedeParqueadero);
        final TipoUsuario tipoUsuario = usuarioDTO.getTipoUsuario() == null ? null : tipoUsuarioRepository.findById(usuarioDTO.getTipoUsuario())
                .orElseThrow(() -> new NotFoundException("tipoUsuario not found"));
        usuario.setTipoUsuario(tipoUsuario);
        return usuario;
    }

    public RespuestaIniciarSesionDTO iniciarSesion (IniciarSesionDTO inicioSesionDto) {
        RespuestaIniciarSesionDTO respuestaObj = new RespuestaIniciarSesionDTO();
        String username = inicioSesionDto.getUsername();
        String password = inicioSesionDto.getPassword();
        Usuario usuario = usuarioRepository.findByUsername(username.toUpperCase());
        if (usuario != null) {
            if (usuario.getPassword().equals(password)) {
                respuestaObj.setNombres(usuario.getNombres());
                respuestaObj.setApellidos(usuario.getApellidos());
                respuestaObj.setUsuarioId(usuario.getId().toString());
                respuestaObj.setRolId(usuario.getTipoUsuario().getId().toString());
                respuestaObj.setRolNombre(usuario.getTipoUsuario().getRol());
                respuestaObj.setUsername(usuario.getUsername());

                final UserDetails userDetails = jwtService.loadUserByUsername(usuario.getUsername());
                final String token = jwtProvider.generateToken(userDetails, usuario);

                respuestaObj.setToken(token);
            } else {
                throw new NotFoundException("Clave incorrecta.");
            }
        } else {
            throw new NotFoundException("Usuario no encontrado.");
        }
        return respuestaObj;
    }

}
