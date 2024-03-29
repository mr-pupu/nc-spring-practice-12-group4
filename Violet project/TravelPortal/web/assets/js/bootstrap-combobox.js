/* =============================================================
 * bootstrap-combobox.js v0.9.1
 * =============================================================
 * Copyright 2012 Daniel Farrell
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ============================================================ */

!function( $ ) {

 "use strict"

  var Combobox = function ( element, options ) {
    this.options = $.extend({}, $.fn.combobox.defaults, options)
    this.$container = this.setup(element)
    this.$element = this.$container.find('input')
    this.$button = this.$container.find('.dropdown-toggle')
    this.$target = this.$container.find('select')
    this.matcher = this.options.matcher || this.matcher
    this.sorter = this.options.sorter || this.sorter
    this.highlighter = this.options.highlighter || this.highlighter
    this.$menu = $(this.options.menu).appendTo('body')
    this.forceMatch = this.options.forceMatch
    this.placeholder = this.options.placeholder || this.placeholder
    this.$element.attr('placeholder', this.placeholder)
    this.shown = false
    this.refresh()
    this.listen()
  }

  /* NOTE: COMBOBOX EXTENDS BOOTSTRAP-TYPEAHEAD.js
     ========================================== */

  Combobox.prototype = $.extend({}, $.fn.typeahead.Constructor.prototype, {

    constructor: Combobox

  , setup: function (element) {
//      console.log("setup");
      var select = $(element)
        , combobox = $(this.options.template)
      select.before(combobox)
      select.detach()
      combobox.prepend(select)
      return combobox
    }

  , parse: function () {
//      console.log("parse");
      var map = {}
        , source = []
        , selected = false
      this.$target.find('option').each(function() {
        var option = $(this)
        map[option.html()] = option.val()
        source.push(option.html())
        if(option.attr('selected')) selected = option.html()
      })
      this.map = map
      if (selected) {
        this.$element.val(selected)
        this.$container.addClass('combobox-selected')
      }
      return source
    }

  , toggle: function () {
//      console.log("toggle");
      console.log(this.$target, this.$element, this.$container);
    if (this.$container.hasClass('combobox-selected')) {
//        console.log(this.$target, this.$element, this.$container);
      this.$target.val('')
//      this.$element.val('').focus()
      this.$container.removeClass('combobox-selected')
      this.toggle()
    } else {
      if (this.shown) {
        this.hide()
      } else {
        this.lookup()
      }
    }
  }

  , refresh: function () {
    this.source = this.parse()
    this.options.items = this.source.length
  }

  // modified typeahead function adding container and target handling
  , select: function () {
//      console.log("select");
      var val = this.$menu.find('.active').attr('data-value')
      this.$element.val(val)
      this.$container.addClass('combobox-selected')
      this.$target.val(this.map[val])
      this.$target.trigger('change')
      return this.hide()
    }

  // modified typeahead function removing the blank handling
  , lookup: function (event) {
//      console.log("lookup");
      var that = this
        , items
        , q
      this.query = ''//this.$element.val()
//      console.log(this.query);
      items = $.grep(this.source, function (item) {
        if (that.matcher(item)) return item
      })

      items = this.sorter(items)

      if (!items.length) {
        return this.shown ? this.hide() : this
      }

      return this.render(items.slice(0, this.options.items)).show()
    }

  // modified typeahead function adding button handling
  , listen: function () {
//      console.log("listen");
      this.$element
        .on('blur',     $.proxy(this.blur, this))
        .on('keypress', $.proxy(this.keypress, this))
        .on('keyup',    $.proxy(this.keyup, this))

      if ($.browser.webkit || $.browser.msie) {
        this.$element.on('keydown', $.proxy(this.keypress, this))
      }

      this.$menu
        .on('click', $.proxy(this.click, this))
        .on('mouseenter', 'li', $.proxy(this.mouseenter, this))

      this.$button
        .on('click', $.proxy(this.toggle, this))
    }

  // modified typeahead function to only hide menu if it is visible
  , blur: function (e) {
      var that = this
      e.stopPropagation()
      e.preventDefault()
      var val = this.$element.val();
      if (this.forceMatch && (val.length == 0 || this.matcher(val) == -1)) {
        this.$element.val("")
        this.$target.val("").trigger('change')
      }
      if (this.shown) {
        setTimeout(function () {that.hide()}, 150)
      }
    }
  })

  /* COMBOBOX PLUGIN DEFINITION
   * =========================== */

  $.fn.combobox = function ( option ) {
    return this.each(function () {
      var $this = $(this)
        , data
        , options = typeof option == 'object' && option
      $this.data('combobox', (data = new Combobox(this, options)))
      if (typeof option == 'string') data[option]()
    })
  }

  $.fn.combobox.defaults = {
  template: '<div class="combobox-container"><input type="text" /><span class="add-on btn dropdown-toggle" data-dropdown="dropdown"><span class="caret"/><span class="combobox-clear"><i class="icon-remove"/></span></span></div>'
  , menu: '<ul class="typeahead typeahead-long dropdown-menu"></ul>'
  , item: '<li><a href="#"></a></li>'
  , forceMatch: false
  , placeholder: null
  }

  $.fn.combobox.Constructor = Combobox

}( window.jQuery )
