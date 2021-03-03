import isExists from "date-fns/isExists";
import isPast from "date-fns/isPast";

function getMonths() {
	return [...Array(12).keys()].map((index) => index + 1);
}

function getDays() {
	return [...Array(31).keys()].map((index) => index + 1);
}

function getYears() {
	const retirementAge = 65;
	const currentYear = new Date().getFullYear();
	const baseYear = currentYear - retirementAge + 1;
	return [...Array(65).keys()].map((index) => index + baseYear);
}

function isExistingDate(year, month, day) {
	// month is zero-based for some reason...
	return isExists(year, month - 1, day);
}

function isPastDate(year, month, day) {
	// month is zero-based for some reason...
	return isPast(new Date(year, month - 1, day));
}

export { getMonths, getDays, getYears, isExistingDate, isPastDate };
