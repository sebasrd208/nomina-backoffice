
@Autowired
MapeoGeneral mapeo;

public void guardar(PersonasDTO persona){
    Map<String, Object> params = new HashMap<>();
    params.put("PA_NOMBRE", persona.getNombre());
    params.put("PA_EDAD", persona.getEdad());
    params.put("PA_GENERO", persona.getGenero());
    params.put("PA_HOBBIE", persona.getHobbie());

    try{
        mapeo.SP_SETPERSONA(params);
    }catch (DataAccessException s){
        throw new RuntimeException(s.getMostSpecificCause().getMessage());
    }
}


@Autowired
PersonasDAO dao;

public PersonasDTO guardar(PersonasDTO persona){
    return dao.save(persona);
}












