package com.commons.utils.constants;

import com.commons.utils.utils.LogAndResponse;

public class Messages {
   /*-> SUCCESS: */
   public static final String MESSAGE_SUCCESS_NEW_ID = "¡El código %d, ha sido generado con exito!";
   public static final String MESSAGE_SUCCESS_LIST_ENTITY = "¡La entidad fué listada con exito!";
   public static final String MESSAGE_SUCCESS_CREATE = "¡Registro exitoso!";
   public static final String MESSAGE_SUCCESS_UPDATE = "¡La entidad fué actualizada con exito!";
   public static final String MESSAGE_SUCCESS_DOWNLOAD = "¡El anexo, se descargo exitosamente!";
   public static final String MESSAGE_SUCCESS_AUTH = "!Acceso permitido¡";
   public static final String MESSAGE_SUCCESS_SEARCH_RESULT = "¡Se encontraron resultados, para : %s!";
   public static final String MESSAGE_SUCCESS_CREATE_USER = "¡El usuario %s, fué creado con exito!";
   public static final String MESSAGE_SUCCESS_UPDATE_USER = "¡El usuario %s, fué actualizado con exito!";
   public static final String MESSAGE_SUCCESS_DELETE_BY_ID = "¡El registro con código %s, fué eliminado exitosamente!";
   public static final String MESSAGE_SUCCESS_SAVE = "¡%s, fué guardado existosamente!";
   public static final String SUCCESS_SAVE_TO_ASSIGN_REVISOR = "¡Revisor: %s, asignado exitosamente!";
   public static final String SUCCESS_SAVE_TO_UNASSIGN_REVISOR = "¡Asignación eliminada!";
   public static final String SUCCESS_SAVE_TO_READ_ASSIGNMENT_REVISOR = "¡El expediente recibido exitosamente!";
   public static final String SUCCESS_UPLOAD_FILE = "¡Archivo guardado exitosamente!";
   public static final String SUCCESS_CREATE_TABLE = "¡Tabla %s, creada exitosamente!";
   public static final String SUCCESS_ALTER_TABLE = "¡Tabla %s, modificada exitosamente!";
   public static final String SUCCESS_ALTER_TABLE_ADD_COLUMN = "¡Se agregados los campos exitosamente en la tabla: %s!";
   public static final String SUCCESS_ALTER_TABLE_EDIT_COLUMN = "¡Tipo de campo cambiado exitosamente!";
   public static final String SUCCESS_ALTER_META_NAME = "¡Nombre cambiado exitosamente!";

   /*-> WARNING: */
   public static final String MESSAGGE_WARNING_EMPTY = "¡No hay registros para mostrar!";
   public static final String MESSAGE_WARNING_ENTITY_FIND_BY_ID = "¡El código %s, no existe en la tabla!";
   public static final String MESSAGE_WARNING_USER_AUTH = "¡Usuario o clave incorrecta!";
   public static final String MESSAGE_WARNING_USER_NOTFOUND = "¡Usuario %s no existe!";
   public static final String MESSAGE_WARNING_DATA_SAVE = "¡Se ha poducido un error inesperado, vuelva intentarlo!";
   public static final String MESSAGE_WARNING_FILE_SAVE = "¡Extension de archivo `.%s`, no soportado!";
   public static final String WARNING_CREATE_TABLE = "¡Ya existe una tabla con el nombre: %s!";
   public static final String WARNING_UPLOAD_TABLE = "¡Archivo: %s, no es compatible!";
   public static final String WARNING_ALTER_TABLE_ADD_COLUMN = "¡Nombre de campo: %s, en uso!";

   /*-> ERROR: */
   public final static String MESSAGGE_ERROR_DATA_ACCESS = "¡Ocurrió un error, contacte a sitemas y proporcione este código %s!";
   public static final String ERROR_FILE_SAVE = "¡Ocurrió un error al guardar el archivo: %s!";

   /*-> -----------------------------METHOD'S--------------------------- */
   /*-> SUCCESS: */
   public static String MESSAGE_SUCCESS_CREATE() {
      return MESSAGE_SUCCESS_CREATE;
   }

   public static String MESSAGE_SUCCESS_UPDATE() {
      return MESSAGE_SUCCESS_UPDATE;
   }

   public static String MESSAGE_SUCCESS_SEARCH_RESULT(String value) {
      return String.format(MESSAGE_SUCCESS_SEARCH_RESULT, value);
   }

   public static String MESSAGE_SUCCESS_CREATE_USER(String newUser) {
      return String.format(MESSAGE_SUCCESS_CREATE_USER, newUser);
   }

   public static String MESSAGE_SUCCESS_UPDATE_USER(String userName){
      return String.format(MESSAGE_SUCCESS_UPDATE_USER, userName);
   }

   public static String MESSAGE_SUCCESS_NEW_ID(Long newId) {
      return String.format(MESSAGE_SUCCESS_NEW_ID, newId);
   }

   public static String MESSAGE_SUCCESS_LIST_ENTITY() {
      return MESSAGE_SUCCESS_LIST_ENTITY;
   }

   public static String MESSAGE_SUCCESS_DELETE_BY_ID(Long id){
      return String.format(MESSAGE_SUCCESS_DELETE_BY_ID, id);
   }

   public static String MESSAGE_SUCCESS_SAVE(String entity){
      return String.format(MESSAGE_SUCCESS_SAVE, entity);
   }

   public static String SUCCESS_SAVE_TO_ASSIGN_REVISOR(String usrRevisor){
      return String.format(SUCCESS_SAVE_TO_ASSIGN_REVISOR, usrRevisor);
   }

   public static String SUCCESS_CREATE_TABLE(String nombreTabla){
      return String.format(SUCCESS_CREATE_TABLE, nombreTabla);
   }

   public static String SUCCESS_ALTER_TABLE(String nombreTabla){
      return String.format(SUCCESS_ALTER_TABLE, nombreTabla);
   }

   public static String SUCCESS_ALTER_TABLE_ADD_COLUMN(String nombreTabla){
      return String.format(SUCCESS_ALTER_TABLE_ADD_COLUMN, nombreTabla);
   }

   /*-> WARNING */
   public static String MESSAGE_WARNING_ENTITY_FIND_BY_ID(long id) {
      return String.format(MESSAGE_WARNING_ENTITY_FIND_BY_ID, id);
   }

   public static String MESSAGGE_WARNING_EMPTY() {
      return MESSAGGE_WARNING_EMPTY;
   }

   public static String MESSAGE_WARNING_USER_NOTFOUND(String login){
      return String.format(MESSAGE_WARNING_USER_NOTFOUND, login);
   }

   public static String MESSAGE_WARNING_FILE_SAVE(String fileName){
      return String.format(MESSAGE_WARNING_FILE_SAVE, fileName);
   }

   public static String WARNING_CREATE_TABLE(String nombreTabla){
      return String.format(WARNING_CREATE_TABLE, nombreTabla);
   }

   public static String WARNING_UPLOAD_TABLE(String fileName){
      return String.format(WARNING_UPLOAD_TABLE, fileName);
   }

   public static String WARNING_ALTER_TABLE_ADD_COLUMN(String fieldName){
      return String.format(WARNING_ALTER_TABLE_ADD_COLUMN, fieldName);
   }

   /*-> ERROR */
   public static String MESSAGGE_ERROR_DATA_ACCESS() {
      return String.format(MESSAGGE_ERROR_DATA_ACCESS, LogAndResponse.getIdLog());
   }

   public static String ERROR_FILE_SAVE(String fileName){
      return String.format(ERROR_FILE_SAVE, fileName);
   }

}
