import React, { useState } from 'react';
import { Calendar, Clock, BookOpen, BarChart3, User, Plus, Bell, CheckCircle2, Circle, Star, ArrowRight } from 'lucide-react';

const StudyAppMockups = () => {
  const [activeScreen, setActiveScreen] = useState('dashboard');

  const BottomNav = ({ active, setActive }) => {
    const navItems = [
      { id: 'dashboard', icon: BarChart3, label: 'Dashboard' },
      { id: 'tasks', icon: CheckCircle2, label: 'Tasks' },
      { id: 'calendar', icon: Calendar, label: 'Calendar' },
      { id: 'analytics', icon: BarChart3, label: 'Analytics' },
      { id: 'profile', icon: User, label: 'Profile' }
    ];

    return (
      <div className="fixed bottom-0 left-0 right-0 bg-white border-t border-gray-200 px-2 py-1">
        <div className="flex justify-around">
          {navItems.map(item => {
            const Icon = item.icon;
            return (
              <button
                key={item.id}
                onClick={() => setActive(item.id)}
                className={`flex flex-col items-center p-2 rounded-lg transition-colors ${
                  active === item.id
                    ? 'text-blue-600 bg-blue-50'
                    : 'text-gray-500 hover:text-gray-700'
                }`}
              >
                <Icon size={20} />
                <span className="text-xs mt-1">{item.label}</span>
              </button>
            );
          })}
        </div>
      </div>
    );
  };

  const DashboardScreen = () => (
    <div className="p-4 pb-20 space-y-4">
      {/* Header */}
      <div className="flex justify-between items-center">
        <div>
          <h1 className="text-2xl font-bold text-gray-900">Welcome back, Sarah!</h1>
          <p className="text-gray-600">Today is Monday, Oct 23</p>
        </div>
        <div className="relative">
          <Bell className="w-6 h-6 text-gray-600" />
          <div className="absolute -top-1 -right-1 w-3 h-3 bg-red-500 rounded-full"></div>
        </div>
      </div>

      {/* Today's Summary */}
      <div className="bg-gradient-to-r from-blue-500 to-purple-600 rounded-xl p-4 text-white">
        <h2 className="text-lg font-semibold mb-2">Today's Focus</h2>
        <div className="flex justify-between">
          <div className="text-center">
            <div className="text-2xl font-bold">3</div>
            <div className="text-sm opacity-90">Due Today</div>
          </div>
          <div className="text-center">
            <div className="text-2xl font-bold">2.5h</div>
            <div className="text-sm opacity-90">Planned Study</div>
          </div>
          <div className="text-center">
            <div className="text-2xl font-bold">87%</div>
            <div className="text-sm opacity-90">This Week</div>
          </div>
        </div>
      </div>

      {/* Upcoming Deadlines */}
      <div className="bg-white rounded-xl shadow-sm border border-gray-100 p-4">
        <h3 className="text-lg font-semibold text-gray-900 mb-3">Upcoming Deadlines</h3>
        <div className="space-y-3">
          <div className="flex items-center justify-between p-3 bg-red-50 rounded-lg border-l-4 border-red-400">
            <div>
              <p className="font-medium text-gray-900">Database Design Project</p>
              <p className="text-sm text-gray-600">CP3406 • Due in 2 hours</p>
            </div>
            <Circle className="w-5 h-5 text-red-500" />
          </div>
          <div className="flex items-center justify-between p-3 bg-yellow-50 rounded-lg border-l-4 border-yellow-400">
            <div>
              <p className="font-medium text-gray-900">React Native Quiz</p>
              <p className="text-sm text-gray-600">CP5307 • Due tomorrow</p>
            </div>
            <Circle className="w-5 h-5 text-yellow-500" />
          </div>
        </div>
      </div>

      {/* Quick Actions */}
      <div className="grid grid-cols-2 gap-3">
        <button className="bg-white rounded-xl shadow-sm border border-gray-100 p-4 text-left hover:bg-gray-50">
          <Plus className="w-6 h-6 text-blue-500 mb-2" />
          <p className="font-medium text-gray-900">Add Task</p>
          <p className="text-sm text-gray-600">Create new assignment</p>
        </button>
        <button className="bg-white rounded-xl shadow-sm border border-gray-100 p-4 text-left hover:bg-gray-50">
          <Clock className="w-6 h-6 text-green-500 mb-2" />
          <p className="font-medium text-gray-900">Start Session</p>
          <p className="text-sm text-gray-600">Begin focused study</p>
        </button>
      </div>
    </div>
  );

  const TasksScreen = () => (
    <div className="p-4 pb-20 space-y-4">
      {/* Header */}
      <div className="flex justify-between items-center">
        <h1 className="text-2xl font-bold text-gray-900">Tasks</h1>
        <button className="bg-blue-500 text-white p-2 rounded-full">
          <Plus size={20} />
        </button>
      </div>

      {/* Filter Tabs */}
      <div className="flex space-x-2 overflow-x-auto">
        {['All', 'Due Today', 'This Week', 'Overdue'].map(filter => (
          <button
            key={filter}
            className={`px-4 py-2 rounded-full text-sm font-medium whitespace-nowrap ${
              filter === 'All'
                ? 'bg-blue-500 text-white'
                : 'bg-gray-100 text-gray-600 hover:bg-gray-200'
            }`}
          >
            {filter}
          </button>
        ))}
      </div>

      {/* Tasks List */}
      <div className="space-y-3">
        <div className="bg-white rounded-xl shadow-sm border border-gray-100 overflow-hidden">
          <div className="p-4 border-l-4 border-red-400">
            <div className="flex items-start justify-between">
              <div className="flex items-start space-x-3">
                <button className="mt-1">
                  <Circle className="w-5 h-5 text-red-500" />
                </button>
                <div>
                  <h3 className="font-medium text-gray-900">Complete Database ER Diagram</h3>
                  <p className="text-sm text-gray-600">CP3406 - Database Systems</p>
                  <p className="text-xs text-red-600 font-medium mt-1">Due in 2 hours</p>
                </div>
              </div>
              <Star className="w-4 h-4 text-yellow-500 fill-current" />
            </div>
          </div>
        </div>

        <div className="bg-white rounded-xl shadow-sm border border-gray-100 overflow-hidden">
          <div className="p-4 border-l-4 border-blue-400">
            <div className="flex items-start justify-between">
              <div className="flex items-start space-x-3">
                <button className="mt-1">
                  <Circle className="w-5 h-5 text-blue-500" />
                </button>
                <div>
                  <h3 className="font-medium text-gray-900">React Native Component Assignment</h3>
                  <p className="text-sm text-gray-600">CP5307 - Mobile App Development</p>
                  <p className="text-xs text-blue-600 font-medium mt-1">Due tomorrow 11:59 PM</p>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div className="bg-white rounded-xl shadow-sm border border-gray-100 overflow-hidden">
          <div className="p-4 border-l-4 border-green-400">
            <div className="flex items-start justify-between">
              <div className="flex items-start space-x-3">
                <button className="mt-1">
                  <CheckCircle2 className="w-5 h-5 text-green-500 fill-current" />
                </button>
                <div>
                  <h3 className="font-medium text-gray-900 line-through">Read Chapter 5: Algorithms</h3>
                  <p className="text-sm text-gray-600">CP3307 - Data Structures</p>
                  <p className="text-xs text-green-600 font-medium mt-1">Completed</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );

  const CalendarScreen = () => (
    <div className="p-4 pb-20 space-y-4">
      {/* Header */}
      <div className="flex justify-between items-center">
        <h1 className="text-2xl font-bold text-gray-900">Calendar</h1>
        <div className="flex space-x-2">
          <button className="px-3 py-1 text-sm bg-blue-500 text-white rounded-lg">Week</button>
          <button className="px-3 py-1 text-sm bg-gray-100 text-gray-600 rounded-lg">Month</button>
        </div>
      </div>

      {/* Week View */}
      <div className="bg-white rounded-xl shadow-sm border border-gray-100 overflow-hidden">
        <div className="grid grid-cols-7 border-b border-gray-100">
          {['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'].map(day => (
            <div key={day} className="p-3 text-center text-sm font-medium text-gray-600 border-r border-gray-100 last:border-r-0">
              {day}
            </div>
          ))}
        </div>
        <div className="grid grid-cols-7 min-h-[300px]">
          {Array.from({ length: 7 }, (_, i) => (
            <div key={i} className="border-r border-gray-100 last:border-r-0 p-2 space-y-1">
              <div className={`text-sm font-medium ${i === 1 ? 'text-blue-600' : 'text-gray-900'}`}>
                {22 + i}
              </div>
              {i === 1 && (
                <div className="space-y-1">
                  <div className="text-xs bg-red-100 text-red-800 p-1 rounded">
                    DB Project Due
                  </div>
                  <div className="text-xs bg-blue-100 text-blue-800 p-1 rounded">
                    CP3406 Lecture
                  </div>
                </div>
              )}
              {i === 2 && (
                <div className="text-xs bg-yellow-100 text-yellow-800 p-1 rounded">
                  RN Quiz Due
                </div>
              )}
              {i === 3 && (
                <div className="text-xs bg-green-100 text-green-800 p-1 rounded">
                  Study Group
                </div>
              )}
            </div>
          ))}
        </div>
      </div>

      {/* Today's Schedule */}
      <div className="bg-white rounded-xl shadow-sm border border-gray-100 p-4">
        <h3 className="text-lg font-semibold text-gray-900 mb-3">Today's Schedule</h3>
        <div className="space-y-3">
          <div className="flex items-center space-x-3 p-3 bg-blue-50 rounded-lg">
            <div className="text-sm font-medium text-blue-600">9:00 AM</div>
            <div className="flex-1">
              <p className="font-medium text-gray-900">CP3406 Database Systems</p>
              <p className="text-sm text-gray-600">Room: IT 142</p>
            </div>
          </div>
          <div className="flex items-center space-x-3 p-3 bg-gray-50 rounded-lg">
            <div className="text-sm font-medium text-gray-600">2:00 PM</div>
            <div className="flex-1">
              <p className="font-medium text-gray-900">Study Session - Database Project</p>
              <p className="text-sm text-gray-600">Library Study Room B</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );

  const AnalyticsScreen = () => (
    <div className="p-4 pb-20 space-y-4">
      {/* Header */}
      <h1 className="text-2xl font-bold text-gray-900">Analytics</h1>

      {/* Stats Overview */}
      <div className="grid grid-cols-2 gap-3">
        <div className="bg-white rounded-xl shadow-sm border border-gray-100 p-4">
          <div className="text-2xl font-bold text-blue-600">24h</div>
          <div className="text-sm text-gray-600">Study Time This Week</div>
        </div>
        <div className="bg-white rounded-xl shadow-sm border border-gray-100 p-4">
          <div className="text-2xl font-bold text-green-600">87%</div>
          <div className="text-sm text-gray-600">Tasks Completed</div>
        </div>
        <div className="bg-white rounded-xl shadow-sm border border-gray-100 p-4">
          <div className="text-2xl font-bold text-purple-600">3.4</div>
          <div className="text-sm text-gray-600">Average GPA</div>
        </div>
        <div className="bg-white rounded-xl shadow-sm border border-gray-100 p-4">
          <div className="text-2xl font-bold text-orange-600">5</div>
          <div className="text-sm text-gray-600">Courses This Semester</div>
        </div>
      </div>

      {/* Study Pattern Chart */}
      <div className="bg-white rounded-xl shadow-sm border border-gray-100 p-4">
        <h3 className="text-lg font-semibold text-gray-900 mb-4">Weekly Study Pattern</h3>
        <div className="space-y-3">
          {['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'].map((day, i) => (
            <div key={day} className="flex items-center space-x-3">
              <div className="w-8 text-sm text-gray-600">{day}</div>
              <div className="flex-1 bg-gray-100 rounded-full h-3">
                <div
                  className="bg-blue-500 h-3 rounded-full transition-all duration-300"
                  style={{ width: `${[80, 60, 90, 70, 40, 30, 20][i]}%` }}
                ></div>
              </div>
              <div className="text-sm text-gray-600 w-10">{[4.2, 3.1, 4.8, 3.7, 2.1, 1.5, 1.0][i]}h</div>
            </div>
          ))}
        </div>
      </div>

      {/* Course Performance */}
      <div className="bg-white rounded-xl shadow-sm border border-gray-100 p-4">
        <h3 className="text-lg font-semibold text-gray-900 mb-3">Course Performance</h3>
        <div className="space-y-3">
          <div className="flex items-center justify-between p-3 bg-green-50 rounded-lg">
            <div>
              <p className="font-medium text-gray-900">CP3406 Database Systems</p>
              <p className="text-sm text-gray-600">15 tasks completed</p>
            </div>
            <div className="text-lg font-bold text-green-600">A-</div>
          </div>
          <div className="flex items-center justify-between p-3 bg-blue-50 rounded-lg">
            <div>
              <p className="font-medium text-gray-900">CP5307 Mobile Development</p>
              <p className="text-sm text-gray-600">12 tasks completed</p>
            </div>
            <div className="text-lg font-bold text-blue-600">B+</div>
          </div>
        </div>
      </div>
    </div>
  );

  const screens = {
    dashboard: DashboardScreen,
    tasks: TasksScreen,
    calendar: CalendarScreen,
    analytics: AnalyticsScreen,
    profile: () => <div className="p-4 pb-20"><h1 className="text-2xl font-bold">Profile Screen</h1><p className="text-gray-600 mt-2">User settings and preferences</p></div>
  };

  const CurrentScreen = screens[activeScreen];

  return (
    <div className="max-w-sm mx-auto bg-gray-50 min-h-screen relative border rounded-xl overflow-hidden shadow-2xl">
      {/* Status Bar */}
      <div className="bg-white px-4 py-2 flex justify-between items-center text-sm">
        <div className="font-medium">9:41</div>
        <div className="flex space-x-1">
          <div className="w-4 h-2 bg-green-500 rounded-sm"></div>
          <div className="w-4 h-2 bg-gray-300 rounded-sm"></div>
          <div className="w-4 h-2 bg-gray-300 rounded-sm"></div>
        </div>
      </div>

      <CurrentScreen />
      <BottomNav active={activeScreen} setActive={setActiveScreen} />

      {/* Screen Navigation */}
      <div className="absolute top-16 right-4 flex flex-col space-y-1 z-10">
        {Object.keys(screens).map(screen => (
          <button
            key={screen}
            onClick={() => setActiveScreen(screen)}
            className={`px-2 py-1 text-xs rounded ${
              activeScreen === screen
                ? 'bg-blue-500 text-white'
                : 'bg-white text-gray-600 shadow-sm'
            }`}
          >
            {screen.charAt(0).toUpperCase() + screen.slice(1)}
          </button>
        ))}
      </div>
    </div>
  );
};

export default StudyAppMockups;