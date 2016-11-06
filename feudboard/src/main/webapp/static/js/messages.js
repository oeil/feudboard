/*
 * Copyright (C) 2016 TekNux.org
 *
 * This file is part of the feudboard GPL Source Code.
 *
 * feudboard Source Code is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * feudboard Source Code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *  
 * You should have received a copy of the GNU General Public License
 * along with dropbitz Community Source Code.  If not, see <http://www.gnu.org/licenses/>.
 */

function message(id, message, type, closable) {
	if (typeof(id) == 'undefined' && typeof(message) == 'undefined') {
		$(".alert").alert('close');
		
		$("#messages").addClass("hidden");
	} else {
		if (typeof(message) == 'undefined') {
			$("#message-id-" + id).alert('close');

			if ($("#messages").children().size() == 0) {
				$("#messages").addClass("hidden");
			}
		} else {
			if (typeof(type) == 'undefined')  {
				type="info";
			}
			if (typeof(closable) == 'undefined')  {
				closable=false;
			}
			
			if ($("#messages").children().size() == 0) {
				$("#messages").removeClass("hidden");
			}
			
			//Duplicate html from messages.ftl
			var html = "<div id=\"message-id-" + id + "\" class=\"alert alert-" + type + " alert-dismissible\" role=\"alert\">";
			if (closable) {
				html += "<button type=\"button\" class=\"close\" data-dismiss=\"alert\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>";
			}
			html += message;
			html += "</div>";
			$("#messages").append(html);
		}
	}
}
