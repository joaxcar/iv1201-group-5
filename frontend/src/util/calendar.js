import isExists from "date-fns/isExists";
import isPast from "date-fns/isPast";

/**
 * Generates an array with values 1-12.
 */
function getMonths() {
	return [...Array(12).keys()].map((index) => index + 1);
}

/**
 * Generates an array with values 1-31.
 */
function getDays() {
	return [...Array(31).keys()].map((index) => index + 1);
}

/**
 * Generates an array with values from (current year - 65) to
 * current year.
 */
function getYears() {
	const retirementAge = 65;
	const currentYear = new Date().getFullYear();
	const baseYear = currentYear - retirementAge + 1;
	return [...Array(65).keys()].map((index) => index + baseYear);
}

/**
 * Validates that a date specified by the parameters exists.
 * @param {number} year Year of the date.
 * @param {number} month Month of the date.
 * @param {number} day Day of the date.
 */
function isExistingDate(year, month, day) {
	// month is zero-based for some reason...
	return isExists(year, month - 1, day);
}

/**
 * Validates that a date specified by the parameters is a past date.
 * @param {number} year Year of the date.
 * @param {number} month Month of the date.
 * @param {number} day Day of the date.
 */
function isPastDate(year, month, day) {
	// month is zero-based for some reason...
	return isPast(new Date(year, month - 1, day));
}

export { getMonths, getDays, getYears, isExistingDate, isPastDate };
