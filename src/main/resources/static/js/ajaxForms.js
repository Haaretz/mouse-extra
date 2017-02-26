/**
 * This module handles ajax-forms.
 * AJAX form is a form that has class js-ajax-form (as default)
 * AJAX form takes the form action and method and use them to submit form with XMLHttpRequest.
 *
 * @module htz-ajax-form
 */

/**
 * Add submit event to form[.selector], that submits the form using AJAX instead of regular submit.
 * @param {string} selector class/ CSS query of the form element
 * @param {function} success sumbit success handler
 * @param {function}error submit error handler
 */
function ajaxform(selector, success, error) {
  if (!selector || selector.trim() === '') {
    throw new Error('selector parameter is null or empty.');
  }

  const forms = document.querySelectorAll(`form${selector}`);
  Array.from(forms).forEach((form) => {
    const action = form.getAttribute('action');
    const method = 'post';

    form.addEventListener('submit', (e) => {
      e.preventDefault();
      const params = [];
      const formData = new FormData(e.target);
      for (const pair of formData.entries()) {
        params.push(`${pair[0]}=${encodeURIComponent(pair[1])}`);
      }
      const promise = fetch(action, {
        method,
        body: params.join('&'),
        headers: new Headers({ 'Content-Type': 'application/x-www-form-urlencoded' }),
      });
      promise.then(success, error);
      return false;
    });
  });
}
ajaxform('#extraForm', function (e) {
  console.log(e);
}, function (e) {
  console.log(e)
});
// onsubmit="function submitForm(e) {
// e.preventDefault(); console.log('form submitted. Event:'); console.log(e); console.log('form data: ');console.log(new FormData(e.target));
// }"
