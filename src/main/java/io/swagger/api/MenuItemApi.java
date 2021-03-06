/*
 * NOTE: This class is auto generated by the swagger code generator program (1.0.11).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import javax.validation.Valid;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.CreatedResponse;
import io.swagger.model.MenuItemDto;
import io.swagger.model.MenuItemOptionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-02-22T11:14:56.538Z")

@Api(value = "menuItem", description = "the menuItem API")
public interface MenuItemApi {

    @ApiOperation(value = "Add a MenuItem, with optional MenuItemOptions.", nickname = "addMenuItem", notes = "Add a MenuItem, complete with MenuItemOptions, to the database. Since MenuItemOptions are always linked to specific MenuItems, they are created, updated, and removed by using the MenuItem API. More options may be added later with the /MenuItem/addOption/ API. ", response = CreatedResponse.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Menu Item Created", response = CreatedResponse.class),
        @ApiResponse(code = 400, message = "Bad Request", response = CreatedResponse.class) })
    @RequestMapping(value = "/menuItem",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<CreatedResponse> addMenuItem(@ApiParam(value = "Complete menu item, with MenuItemOptions", required = true) @Valid @RequestBody MenuItemDto menuItem);


    @ApiOperation(value = "Add a menuItemOption", nickname = "addMenuItemOption", notes = "Add a MenuItemOption to a MenuItem. ", response = CreatedResponse.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "MenuItemOption added", response = CreatedResponse.class),
        @ApiResponse(code = 400, message = "Bad Request", response = CreatedResponse.class) })
    @RequestMapping(value = "/menuItem/addOption/{menuItemId}",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<CreatedResponse> addMenuItemOption(@ApiParam(value = "ID of the MenuItem getting the new option", required = true) @PathVariable("menuItemId") Integer menuItemId,@ApiParam(value = "MenuItemOption", required = true) @Valid @RequestBody MenuItemOptionDto option);


    @ApiOperation(value = "Delete a MenuItemOption", nickname = "deleteOption", notes = "Delete a MenuItemOption from a MenuItem.", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "MenuItemOption deleted"),
        @ApiResponse(code = 400, message = "Bad Request", response = CreatedResponse.class) })
    @RequestMapping(value = "/menuItem/deleteOption/{optionId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteOption(@ApiParam(value = "ID of the MenuItemOpton to delete", required = true) @PathVariable("optionId") Integer optionId);


    @ApiOperation(value = "Gets a menuItem by ID", nickname = "getMenuItem", notes = "Gets a MenuItem by its ID.", response = MenuItemDto.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Found", response = MenuItemDto.class),
        @ApiResponse(code = 404, message = "NotFound") })
    @RequestMapping(value = "/menuItem/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<MenuItemDto> getMenuItem(@ApiParam(value = "id of menuItem to find", required = true) @PathVariable("id") Integer id);

}
